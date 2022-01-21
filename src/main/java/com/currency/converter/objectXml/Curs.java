package com.currency.converter.objectXml;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@Data
public class Curs {
    @XmlElement(name = "NumCode")
    private String NumCode;
    @XmlElement(name = "CharCode")
    private String CharCode;
    @XmlElement(name = "Nominal")
    private Integer Nominal;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "Value")
    private String Value;
}
