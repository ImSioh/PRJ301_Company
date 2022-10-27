/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.CustomerDAO;

public class AccountProfileEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("accSession") == null) {
            response.sendRedirect("signin");
        } else {
            Account acc = (Account) request.getSession().getAttribute("accSession");
            String cID = acc.getCustomerID();
            Customer cus = new CustomerDAO().getCustomerById(cID);
            request.setAttribute("customer", cus);
            request.setAttribute("acc", acc);
            request.getRequestDispatcher("../profile-edit.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cusID = request.getParameter("id");
        String compName = request.getParameter("compName");
        String contName = request.getParameter("contName");
        String contTitle = request.getParameter("contTitle");
        String address = request.getParameter("address");
//        String email = request.getParameter("email");

        Customer c = new Customer(cusID, compName, contName, contTitle, address);

        if (new CustomerDAO().editProfile(c) != 0) {
            response.sendRedirect("profile");
        } else {
            request.getRequestDispatcher("profile-edit");
        }
    }
}
