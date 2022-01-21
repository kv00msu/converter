package com.currency.converter.repository;

import com.currency.converter.entity.CursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.sql.Date;


@Repository
public interface CursRepository extends JpaRepository<CursEntity, Long> {
    List<CursEntity> getByDate(Date date);
    @Query("select p from CursEntity p where p.charCode=:code and p.date = :date")
    CursEntity getByCharCodeAndDate(String code, Date date);
}
