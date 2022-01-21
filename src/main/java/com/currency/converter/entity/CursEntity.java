package com.currency.converter.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class CursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String numCode;
    @Column
    private String charCode;
    @Column
    private Integer nominal;
    @Column
    private String name;
    @Column
    private Float value;
    @Column
    private Date date;
}
