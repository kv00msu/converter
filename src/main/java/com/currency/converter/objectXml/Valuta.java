package com.currency.converter.objectXml;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Valuta")
@XmlRootElement(name="Valuta", namespace = "")
@Data
public class Valuta {
    @XmlElement(name = "Item")
    private List<Item> item;

}
