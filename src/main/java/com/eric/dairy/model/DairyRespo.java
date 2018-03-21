package com.eric.dairy.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DairyRespo extends JpaRepository<Dairy, Integer> {
    List<Dairy> findByDescription(String description);

    List<Dairy> findAllByDate(Date date);

    @Query(value = "select date, SUM(calories) as calories from dairy group by date order by calories desc ;"
            , nativeQuery = true)
    List<Object[]> findCaloriesByDate();

    @Query(value = "select SUM(calories) as calories from dairy where date = ?1 ;",
            nativeQuery = true)
    int findCaloryByDate(String time);
}
