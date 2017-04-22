package com.isacademy.jjdd1.czterystrony.instruments;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FinancialInstrument {
    private String id;
    private String name;
    private List<Rating> ratings = new ArrayList<>();
    private LocalDate currentRatingDate;
    private BigDecimal currentRatingValue;

    public static class Builder {
        private String id;
        private String name;
        private List<Rating> ratings = new ArrayList<>();

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRatings(List<Rating> ratings) {
            this.ratings = ratings;
            return this;
        }

        public FinancialInstrument build() {
            return new FinancialInstrument(this);
        }
    }

    public FinancialInstrument(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.ratings = builder.ratings;
        this.currentRatingDate = getCurrentRating().getDate();
        this.currentRatingValue = getCurrentRating().getCloseValue();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public Rating getRatingAtDate(LocalDate date) {
        return ratings.stream()
                .filter(t -> t.getDate().equals(date))
                .findFirst()
                .orElse(null);
    }

    public Rating getCurrentRating() {
        return Collections.max(ratings, Comparator.comparing(Rating::getDate));
    }

    public LocalDate getCurrentRatingDate() {
        return currentRatingDate;
    }

    public BigDecimal getCurrentRatingValue() {
        return currentRatingValue;
    }
}