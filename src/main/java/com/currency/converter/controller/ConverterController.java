package com.currency.converter.controller;

import com.currency.converter.entity.CursEntity;
import com.currency.converter.entity.History;
import com.currency.converter.objectXml.CharCode;
import com.currency.converter.objectXml.Curs;
import com.currency.converter.objectXml.ValCurs;
import com.currency.converter.objectXml.Valuta;
import com.currency.converter.service.CursService;
import com.currency.converter.service.HistoryService;
import com.currency.converter.service.UserService;
import com.currency.converter.xml.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller()
@RequestMapping("/converter")
public class ConverterController {
    @Autowired
    public UserService userService;
    @Autowired
    public CursService cursService;
    @Autowired
    public HistoryService historyService;
    @GetMapping()
    public String valuta(Model model, @ModelAttribute("charCode") CharCode charCode, @RequestParam(name = "from", required = false) String from, @RequestParam(name = "to", required = false) String to, @RequestParam(name = "value", required = false) Float value) throws JAXBException, IOException {
        XML xml = new XML();
        BufferedReader buf =xml.getXml("https://cbr.ru/scripts/XML_valFull.asp?d=0");
        JAXBContext context = JAXBContext.newInstance(Valuta.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Valuta valuta = (Valuta) unmarshaller.unmarshal(buf);
        model.addAttribute("valutes", valuta.getItem());
        model.addAttribute("value", value);
        model.addAttribute("from",from);
        model.addAttribute("to",to);
        return "valuta";
    }

    @PostMapping()
    public String Curs(RedirectAttributes redirectAtt, @ModelAttribute("charCode") CharCode charCode, @RequestParam("count") String count) throws IOException, JAXBException, ParseException {
        String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        if (cursService.getByDate(Date.valueOf(date1)).isEmpty()) {
            XML xml = new XML();
            BufferedReader buf = xml.getXml("https://cbr.ru/scripts/XML_daily_eng.asp" + "?date_req=" + date);
            JAXBContext context = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(buf);
            for (Curs i : valCurs.getCurs()) {
                CursEntity cursEntity = new CursEntity();
                cursEntity.setCharCode(i.getCharCode());
                cursEntity.setNominal(i.getNominal());
                cursEntity.setNumCode(i.getNumCode());
                cursEntity.setName(i.getName());
                cursEntity.setValue(Float.parseFloat(i.getValue().replace(',','.')));
                cursEntity.setDate(Date.valueOf(date1));
                cursService.save(cursEntity);
            }
        }
        CursEntity cursFrom = cursService.getByCharCodeAndDate(charCode.getCharCodeFrom(), Date.valueOf(date1));
        CursEntity cursTo = cursService.getByCharCodeAndDate(charCode.getCharCodeTo(), Date.valueOf(date1));
        Float res = 0.0F;
        res = (cursFrom.getValue() *  Float.parseFloat(count.replace(',','.'))) / cursFrom.getNominal() / cursTo.getValue();
        History history = new History(Date.valueOf(date1), cursFrom.getName(), cursTo.getName(), cursFrom.getCharCode(), cursTo.getCharCode(), Float.parseFloat(count.replace(',','.')), res);
        historyService.save(history);
        redirectAtt.addAttribute("value", res);
        redirectAtt.addAttribute("from", cursFrom.getName());
        redirectAtt.addAttribute("to", cursTo.getName());
        return "redirect:/converter";
    }

}
