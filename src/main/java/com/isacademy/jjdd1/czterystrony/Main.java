package com.isacademy.jjdd1.czterystrony;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.utilities.GlobalExtremaFinder;
import com.isacademy.jjdd1.czterystrony.utilities.LocalExtremaFinder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        findLocalExtremaForGivenInvestFundTest("AVIVA Obligacji");
        findLocalExtremaOfAllInvestFundsTest();
        printAllInvestFundsByNameTest();
        printAllInvestFundsByPriorityTest();
        findGlobalExtremaForGivenInvestFundTest("AVIVA Obligacji");
    }

    public static void findLocalExtremaForGivenInvestFundTest(String investFundName) {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();
        InvestFund investFund = investFundsDao.get(investFundName);

        LocalExtremaFinder localExtremaFinder = new LocalExtremaFinder(investFund);
        List<Rating> extremaRatings = localExtremaFinder.findExtrema(20);

        System.out.println("Local maximum extrema (found " + extremaRatings.size() + " ratings):");
        for (Rating rating : extremaRatings) {
            System.out.println(rating);
        }
    }

    public static void findLocalExtremaOfAllInvestFundsTest() {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();

        for (InvestFund investFund : investFundsDao.getAllByName()) {
            LocalExtremaFinder localExtremaFinder = new LocalExtremaFinder(investFund);
            List<Rating> extremaRatings = localExtremaFinder.findExtrema(30);

            System.out.println("\n" + investFund.getName() + " || Local maximum extrema (found " + extremaRatings.size() + " ratings):");
            for (Rating rating : extremaRatings) {
                System.out.println(rating);
            }
        }
    }

    public static void findGlobalExtremaForGivenInvestFundTest(String investFundName) {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();
        InvestFund investFund = investFundsDao.get(investFundName);

        GlobalExtremaFinder globalExtremaFinder = new GlobalExtremaFinder(investFund);

        System.out.println("\n\n" + investFund.getName() + " || Global extrema:");
        System.out.println("Maximum: " + globalExtremaFinder.getGlobalMaximum());
        System.out.println("Minimum: " + globalExtremaFinder.getGlobalMinimum());
    }

    public static void printAllInvestFundsByNameTest() {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();

        System.out.println("\n\nInvest funds sorted by name:");
        for (InvestFund investFund : investFundsDao.getAllByName()) {
            System.out.println(investFund.getName());
        }
    }

    public static void printAllInvestFundsByPriorityTest() {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();

        System.out.println("\n\nInvest funds sorted by priority:");
        for (InvestFund investFund : investFundsDao.getAllByPriority()) {
            System.out.println(investFund.getId() + " " + -investFund.getPriority());
        }
    }
}