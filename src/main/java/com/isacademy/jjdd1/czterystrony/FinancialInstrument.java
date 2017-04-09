package com.isacademy.jjdd1.czterystrony;

import java.util.Set;
import java.util.TreeSet;

public abstract class FinancialInstrument {
    private String name;
    private Set<Rating> ratings = new TreeSet<>();

    public FinancialInstrument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public Set<Rating> getAllRatings() {
        return ratings;
    }
}