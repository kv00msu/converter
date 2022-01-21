package com.currency.converter.objectXml;

import lombok.Data;

import java.sql.Date;

@Data
public class CharCode {
    private String charCodeFrom;
    private String charCodeTo;
    private Date date;
}
