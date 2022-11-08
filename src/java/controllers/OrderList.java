/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import models.*;

public class OrderList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int page = 0;
            int elements = 10;
            int numberOfPage;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
                page = 1;
            }
            request.setAttribute("page", page);

        if (request.getParameter("action") == null) {
            String keyword = request.getParameter("txtSearch");
            ArrayList<Orders> odList = new ArrayList<>();
            OrderDAO oDAO = new OrderDAO();

            if (keyword == null) {
                odList = new OrderDAO().getOrdersByPage(page, elements);
                numberOfPage = (int) Math.ceil(oDAO.getAllOrder().size() / (float)elements);

            } else {
                keyword = keyword.trim();
                
                HashMap<String, String> filters = new HashMap<>();
                filters.put("StartOrderDate", request.getParameter("txtStartOrderDate"));
                filters.put("EndOrderDate", request.getParameter("txtEndOrderDate"));
                
                odList = new OrderDAO().getAllOrderKeywordPaging(keyword, filters, page, elements);
                numberOfPage = (int) Math.ceil(oDAO.getAllOrderKeyword(keyword, filters).size() / (float)elements);
            }
            
            
            request.setAttribute("numberOfPage", numberOfPage);

            ArrayList<Employee> empList = new EmployeeDAO().getAllEmployee();
            ArrayList<Customer> cusList = new CustomerDAO().getCustomer();

            request.setAttribute("order", odList);
            request.setAttribute("employee", empList);
            request.setAttribute("customer", cusList);

            request.getRequestDispatcher("order.jsp").forward(request, response);

        } else if (request.getParameter("action").equals("cancel")) {
            int orderID = Integer.parseInt(request.getParameter("id"));
            int rs = new OrderDAO().cancelOrder(orderID);

            response.sendRedirect("order-list");
//            OrderDAO oDAO = new OrderDAO();
//
//            if (request.getParameter("action") == null) {
//                String keyword = request.getParameter("txtSearch");
//                ArrayList<Orders> odList = new ArrayList<>();
//                if (keyword == null || keyword.trim().equals("")) {
//                    odList = new OrderDAO().getOrdersByPage(page, elements);
//                } else {
//                    odList = new OrderDAO().getAllOrderKeyword(keyword);
//                }
//
//                numberOfPage = oDAO.getAllOrder().size() % elements == 0 ? oDAO.getAllOrder().size() / elements : oDAO.getAllOrder().size() / elements + 1;
//                request.setAttribute("numberOfPage", numberOfPage);
//
//                ArrayList<Employee> empList = new EmployeeDAO().getAllEmployee();
//                ArrayList<Customer> cusList = new CustomerDAO().getCustomer();
//
//                request.setAttribute("order", odList);
//                request.setAttribute("employee", empList);
//                request.setAttribute("customer", cusList);
//
//                request.getRequestDispatcher("order.jsp").forward(request, response);
//
//            } else if (request.getParameter("action").equals("cancel")) {
//                int orderID = Integer.parseInt(request.getParameter("id"));
//                int rs = new OrderDAO().cancelOrder(orderID);
//                response.sendRedirect("order-list");
//            }
        } 

    }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
