package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.clients.InvestFundPopularityReportClient;
import com.isacademy.jjdd1.czterystrony.clients.InvestFundZigzagReportClient;
import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundPopularity;
import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundZigzagReport;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/4analysis/raportyekstremalokalne")
public class LocalExtremeDiffsReportsServlet extends HttpServlet {


    @Inject
    InvestFundZigzagReportClient zigZagClient;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        List<InvestFundZigzagReport> zigzagReports = zigZagClient.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/localextremeraport.jsp");
        req.setAttribute("zigZagPopularities", zigzagReports);
        dispatcher.forward(req, resp);
    }
}
