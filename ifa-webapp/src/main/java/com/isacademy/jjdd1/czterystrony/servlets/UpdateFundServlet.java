package com.isacademy.jjdd1.czterystrony.servlets;

import isacademy.jjdd1.czterystrony.webapp.persistence.dbviews.Views;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundFacade;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/4analysis/updatefund")
public class UpdateFundServlet extends HttpServlet {

    @Inject
    InvestFundRepository investFundRepository;

    @Inject
    InvestFundFacade investFundFacade;

    @Inject
    Views views;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        List<InvestFund> investFunds = investFundRepository.getAll();
        req.setAttribute("investFunds", investFunds);
        req.getRequestDispatcher("/updatefund.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        int priority = Integer.parseInt(req.getParameter("priority"));

        try {
            investFundFacade.updateName(id, name);
            investFundFacade.updatePriority(id, priority);
            views.updateViews();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        doGet(req, resp);
    }
}
