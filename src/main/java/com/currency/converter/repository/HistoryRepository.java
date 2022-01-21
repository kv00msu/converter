package com.currency.converter.repository;

import com.currency.converter.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("select p from History p where p.charCodeFrom=:from and p.date = :date and p.charCodeTo=:to")
    List<History> getHistory(String from, String to, Date date);
}
