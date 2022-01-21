package com.currency.converter.objectXml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="ValCurs")
@XmlRootElement(name="ValCurs", namespace = "")
@Data
public class ValCurs {
    @XmlElement(name = "Valute")
    private List<Curs> curs;
}
