package com.isacademy.jjdd1.czterystrony.factories;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Rating;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RatingFactory {
    private static final String DATE_PATTERN = "yyyyMMdd";
    private static final int DATE_COLUMN_INDEX_IN_DATA_FILE = 1;
    private static final int OPEN_COLUMN_INDEX_IN_DATA_FILE = 2;
    private static final int HIGH_COLUMN_INDEX_IN_DATA_FILE = 3;
    private static final int LOW_COLUMN_INDEX_IN_DATA_FILE = 4;
    private static final int CLOSE_COLUMN_INDEX_IN_DATA_FILE = 5;

    public static Rating create(String record) {
        String[] splittedRecord = record.split(",");

        return new Rating.Builder()
                .withDate(parseDateFrom(splittedRecord[DATE_COLUMN_INDEX_IN_DATA_FILE]))
                .withOpen(parseValueFrom(splittedRecord[OPEN_COLUMN_INDEX_IN_DATA_FILE]))
                .withHigh(parseValueFrom(splittedRecord[HIGH_COLUMN_INDEX_IN_DATA_FILE]))
                .withLow(parseValueFrom(splittedRecord[LOW_COLUMN_INDEX_IN_DATA_FILE]))
                .withClose(parseValueFrom(splittedRecord[CLOSE_COLUMN_INDEX_IN_DATA_FILE]))
                .build();
    }

    private static LocalDate parseDateFrom(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, dateTimeFormatter);
    }

    private static BigDecimal parseValueFrom(String value) {
        return new BigDecimal(Double.parseDouble(value)).setScale(2, RoundingMode.HALF_UP);
    }
}
