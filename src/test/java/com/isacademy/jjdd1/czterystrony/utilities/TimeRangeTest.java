package com.isacademy.jjdd1.czterystrony.utilities;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class TimeRangeTest {

    private static LocalDate startDate;
    private static LocalDate endDate;
    private static TimeRange timeRange;

    @Test
    public void should_calculate_one_non_leap_year_difference_in_days() {
        startDate = LocalDate.parse("2014-01-01");
        endDate = LocalDate.parse("2015-01-01");
        timeRange = new TimeRange(startDate, endDate);
        assertThat(timeRange.getDifferenceInDays()).isEqualTo(365);
    }

    @Test
    public void should_calculate_one_leap_year_difference_in_days() {
        startDate = LocalDate.parse("2016-02-01");
        endDate = LocalDate.parse("2017-02-01");
        timeRange = new TimeRange(startDate, endDate);
        assertThat(timeRange.getDifferenceInDays()).isEqualTo(366);
    }

    @Test
    public void start_date_should_be_equal_to_end_date() {
        startDate = LocalDate.parse("2015-01-01");
        endDate = startDate;
        timeRange = new TimeRange(startDate, endDate);
        assertThat(timeRange.getStart()).isEqualTo(timeRange.getEnd());
    }
}