package com.currency.converter.service;

import com.currency.converter.entity.CursEntity;
import com.currency.converter.repository.CursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CursService {
    @Autowired
    public CursRepository cursRepository;

    public List<CursEntity> getByDate(Date date) {
        return cursRepository.getByDate(date);
    }

    public void save(CursEntity curs) {
        cursRepository.save(curs);
    }

    public CursEntity getByCharCodeAndDate(String code, Date date) {
        return cursRepository.getByCharCodeAndDate(code, date);
    }
}
