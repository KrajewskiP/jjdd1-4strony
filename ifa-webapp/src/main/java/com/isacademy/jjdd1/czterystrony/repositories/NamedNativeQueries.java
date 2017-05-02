package com.isacademy.jjdd1.czterystrony.repositories;

public final class NamedNativeQueries {
    private NamedNativeQueries() {
    }

    //language=MySQL
    public static final String investFundDetails = "SELECT " +
            "f.name AS name, " +
            "f.id AS id, " +
            "f.priority AS priority, " +
            "tab2.date AS date, " +
            "tab2.close AS close, " +
            "tab2.diff AS diff " +
            "FROM InvestFund AS f JOIN (SELECT r1.investFund_id, r1.date, r1.close, " +
            "ROUND((r1.close - r2.close)/r2.close*100, 2) AS diff " +
            "FROM (SELECT r.investFund_id, r.date, r.close, r.row_number " +
            "FROM (SELECT Rating. *, (@i \\:= IF(@fund = Rating.investFund_id, @i +1, " +
            "IF(@fund \\:= Rating.investFund_id, 1, 1))) AS row_number " +
            "FROM Rating CROSS JOIN (SELECT @i \\:= 0, @fund \\:= NULL)c " +
            "ORDER BY Rating.investFund_id, Rating.date DESC) AS r " +
            "WHERE r.row_number <= 2) AS r1 " +
            "JOIN (SELECT r.investFund_id, r.date, r.close, r.row_number " +
            "FROM (SELECT Rating. *, (@i \\:= IF(@fund = Rating.investFund_id,@i +1, " +
            "IF(@fund \\:= Rating.investFund_id, 1, 1))) AS row_number " +
            "FROM Rating CROSS JOIN (SELECT @i \\:= 0, @fund \\:= NULL) c " +
            "ORDER BY Rating.investFund_id, Rating.date DESC) AS r " +
            "WHERE r.row_number <= 2) AS r2 ON r1.investFund_id = r2.investFund_id " +
            "WHERE r1.row_number = 1 AND r2.row_number = 2) tab2 " +
            "ON f.id = tab2.investFund_id";
}