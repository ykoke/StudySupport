package com.example.demo.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "countdown_dates")
public class LessonCountdownModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lesson")
    private String lesson;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "userid")
    private Integer userId;

    public long getDaysUntil() {
        LocalDate now = LocalDate.now();
        return ChronoUnit.DAYS.between(now, this.date);
    }
}