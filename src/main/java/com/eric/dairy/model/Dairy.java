package com.eric.dairy.model;

import com.eric.dairy.utils.DairyDeserialization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.sql.Date;
import java.text.ParseException;


@JsonDeserialize(using = DairyDeserialization.class)
@Entity
@Table(name = "dairy")
public class Dairy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "calories", nullable = false)
    private int calories;

    @Column(name = "mark", nullable = false)
    private boolean isMarked;

    public Dairy(){}

    public Dairy(String date, String description, int calories) throws ParseException {

        long timestamp = Long.valueOf(date);
        this.date = new Date(timestamp);
        System.out.println(this.date);
        this.description = description;
        this.calories = calories;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

