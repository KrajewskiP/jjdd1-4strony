package com.isacademy.jjdd1.czterystrony.instruments;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rating implements Comparable<Rating>, Subtractable<Rating> {
    private LocalDate date;
    private BigDecimal closeValue;

    public Rating(LocalDate date, BigDecimal closeValue) {
        this.date = date;
        this.closeValue = closeValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getCloseValue() {
        return closeValue;
    }

    @Override
    public int compareTo(Rating rating) {
        return date.compareTo(rating.getDate());
    }

    @Override
    public BigDecimal subtract(Rating rating) {
        return closeValue.subtract(rating.getCloseValue());
    }

    @Override
    public String toString() {
        return "Data: " + date + " | Close value: " + closeValue;
    }
}