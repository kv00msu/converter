package com.currency.converter.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date date;
    @Column
    private String nameFrom;
    @Column
    private String nameTo;
    @Column
    private String charCodeFrom;
    @Column
    private String charCodeTo;
    @Column
    private Float valueFrom;
    @Column
    private Float valueTo;
    public History(Date date, String nameFrom, String nameTo, String charCodeFrom, String charCodeTo, Float valueFrom, Float valueTo){
        this.date = date;
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
        this.charCodeFrom = charCodeFrom;
        this.charCodeTo = charCodeTo;
        this.valueFrom = valueFrom;
        this.valueTo = valueTo;
    }
    public History(){}
}
