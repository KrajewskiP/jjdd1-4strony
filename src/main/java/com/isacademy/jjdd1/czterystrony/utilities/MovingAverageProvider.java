package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;

import java.util.List;
import java.util.stream.Collectors;

public class MovingAverageProvider extends StatisticsProvider {
    private MovingAverage movingAverage;

    public MovingAverageProvider(FinancialInstrument financialInstrument, MovingAverage movingAverage) {
        super(financialInstrument);
        this.movingAverage = movingAverage;
    }

    public MovingAverageProvider(FinancialInstrument financialInstrument, TimeRange timeRange, MovingAverage movingAverage) {
        super(financialInstrument, timeRange);
        this.movingAverage = movingAverage;
    }

    public List<Rating> getMovingAverageRatings() {
        return ratings.stream()
                .map(t -> mapRatingToAverage(t))
                .collect(Collectors.toList());
    }

    private Rating mapRatingToAverage(Rating rating) {
        movingAverage.add(rating.getCloseValue());
        return new Rating(rating.getDate(), movingAverage.getAverage());
    }

    public static void main(String[] args) {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();
        InvestFund investFund = investFundsDao.get("AGIO Agresywny");

//        TimeRange timeRange = new TimeRange(LocalDate.parse("2010-01-01"), LocalDate.parse("2014-01-01"));

        SimpleMovingAverage movingAverage = new SimpleMovingAverage(10);
//        WeightedMovingAverage movingAverage = new WeightedMovingAverage(10);

        MovingAverageProvider movingAverageProvider = new MovingAverageProvider(investFund, movingAverage);

        List<Rating> averageRatings = movingAverageProvider.getMovingAverageRatings();

        List<Rating> ratings = investFund.getRatings();

        System.out.println("Moving average values (found " + averageRatings.size() + " ratings):");
        for (Rating rating : averageRatings) {
            System.out.println(rating);
        }

        System.out.println("\n\nRatings (found " + ratings.size() + " ratings):");
        for (Rating rating : ratings) {
            System.out.println(rating);
        }
    }
}