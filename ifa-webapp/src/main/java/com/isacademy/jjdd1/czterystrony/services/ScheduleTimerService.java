package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.dbviews.Views;
import com.isacademy.jjdd1.czterystrony.updaters.DatabaseUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class ScheduleTimerService {

    private static Logger log = LoggerFactory.getLogger(ScheduleTimerService.class);

    @EJB
    private DatabaseUpdater databaseUpdater;

    @EJB
    private Views views;

    @Schedule(dayOfWeek = "Mon-Fri", hour = "10", minute = "30", persistent = false)
    void updateDatabase() {
        databaseUpdater.update();
        log.info("Database updated.");
    }

    @Schedule(dayOfWeek = "Mon-Fri", hour = "10", minute = "35", persistent = false)
    void updateViews() {
        views.updateViews();
        log.info("Views updated.");
    }
}