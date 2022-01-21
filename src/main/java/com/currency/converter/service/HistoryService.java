package com.currency.converter.service;

import com.currency.converter.entity.History;
import com.currency.converter.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;


@Service
public class HistoryService {
    @Autowired
    public HistoryRepository historyRepository;

    public void save(History history) {
        historyRepository.save(history);
    }

    public  List<History> getHistory(String from , String to, Date date) {
        return historyRepository.getHistory(from, to, date);
    }
}
