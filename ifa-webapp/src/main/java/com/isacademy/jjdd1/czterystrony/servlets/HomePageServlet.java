package com.isacademy.jjdd1.czterystrony.servlets;

import com.isacademy.jjdd1.czterystrony.dbviews.Views;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/4analysis")
public class HomePageServlet extends HttpServlet {

    private boolean dataFound;

    @EJB
    Views views;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        if (!req.getRequestURI().equals("/4analysis")) {
            resp.sendRedirect("http://localhost:8080/4analysis");
            return;
        }

        List<Object[]> allInvestFunds = views.getAllFunds();
        List<Object[]> promotedInvestFunds = views.getPromotedFunds();
        List<Object[]> otherInvestFunds = views.getNotPromotedFunds();

        if (allInvestFunds.isEmpty()) {
            dataFound = false;
        } else {
            dataFound = true;
        }

        req.setAttribute("promotedInvestFunds", promotedInvestFunds);
        req.setAttribute("otherInvestFunds", otherInvestFunds);
        req.setAttribute("allInvestFunds", allInvestFunds);
        req.setAttribute("dataFound", dataFound);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

}
