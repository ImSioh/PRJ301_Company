/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author phamt
 */
public class OrderManage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        String cID = acc.getCustomerID();
        Customer cus = new CustomerDAO().getCustomerById(cID);
        req.setAttribute("customer", cus);

        String path = req.getContextPath();

        if (req.getParameter("action").equals("display")) {
            ArrayList<Orders> orders = new OrderDAO().getOrdersByCustomerID(cID, req.getParameter("subset"));
            req.setAttribute("orders", orders);
            req.getRequestDispatcher("profile1.jsp").forward(req, resp);
        } else if (req.getParameter("action").equals("cancel")) {
            int cancelId = Integer.parseInt(req.getParameter("cancelId"));
            int rs = new OrderDAO().cancelOrder(cancelId, cID);

//        ArrayList<Order> orders = new OrderDAO().getOrdersByCustomerID(customerID, req.getParameter("subset"));
//        req.setAttribute("orders", orders);
//        req.getRequestDispatcher("ordermanage?action=display&subset=all").forward(req, resp);
            resp.sendRedirect("order-manage?action=display&subset=all");

        }

    }

}
