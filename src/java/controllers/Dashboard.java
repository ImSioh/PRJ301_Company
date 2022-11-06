/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.DashboardDAO;

/**
 *
 * @author phamt
 */
@SuppressWarnings("unchecked")
public class Dashboard extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        List sales_by_month = new DashboardDAO().getSalesByMonth();
        ArrayList<Integer> sales = (ArrayList<Integer>) sales_by_month.get(0);
        ArrayList<String> months = (ArrayList<String>) sales_by_month.get(1);
        
//        resp.getWriter().print(sales);
//        resp.getWriter().print(months);

        req.setAttribute("sales", sales);
        req.setAttribute("months", months);
        
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        
    }
    
}
