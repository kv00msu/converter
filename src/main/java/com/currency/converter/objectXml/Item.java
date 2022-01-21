package com.currency.converter.objectXml;

import lombok.Data;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;

@Data
public class Item {
        @XmlElement(name = "Name")
        private String Name;
        @XmlElement(name = "EngName")
        private String EngName;
        @XmlElement(name = "ParentCode")
        private String ParentCode;
        private Integer ISO_Num_Code;
        private String ISO_Char_Code;
}
