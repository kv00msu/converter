package com.currency.converter.controller;

import com.currency.converter.entity.History;
import com.currency.converter.objectXml.CharCode;
import com.currency.converter.objectXml.Valuta;
import com.currency.converter.service.HistoryService;
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
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    public HistoryService historyService;

    @GetMapping
    public String allHistory(Model model, @RequestParam(name = "history", required = false) List<History> history, @ModelAttribute("charCode") CharCode charCode) throws IOException, JAXBException {
        XML xml = new XML();
        System.out.println(charCode);
        BufferedReader buf =xml.getXml("https://cbr.ru/scripts/XML_valFull.asp?d=0");
        JAXBContext context = JAXBContext.newInstance(Valuta.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Valuta valuta = (Valuta) unmarshaller.unmarshal(buf);
        model.addAttribute("valutes", valuta.getItem());
        model.addAttribute("history", history);
        return "history";
    }
    @PostMapping
    public String getHistory(RedirectAttributes redirectAttributes, @ModelAttribute("charCode") CharCode charCode) {
        redirectAttributes.addAttribute("history", historyService.getHistory(charCode.getCharCodeFrom(), charCode.getCharCodeTo(), charCode.getDate()));
        return "redirect:/history";
    }
}
