/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.Account;
import dal.Cart;
import dal.Customer;
import dal.Item;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import models.CustomerDAO;
import models.OrderDAO;

/**
 *
 * @author Asus
 */
public class CartOrder extends HttpServlet {

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
            out.println("<title>Servlet CartOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartOrder at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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

        //CUSTOMER
        //tao doi tuong customer
//        Customer cus = null;
        Customer cus = new Customer();
        int checkCus = 0;
        //neu customer chua co tai khoan thi add thong tin mua hang
        if (request.getSession().getAttribute("accSession") == null) {
            String compName = request.getParameter("compName");
            String contName = request.getParameter("contName");
            String contTitle = request.getParameter("contTitle");
            String address = request.getParameter("address");
            String cusID = randomString(5);

            //validate
            String msgcompName = "";
            String msgcontName = "";
            String msgcontTitle = "";
            String msgaddress = "";

            if (compName.equals("")) {
                msgcompName = "Company name is required.";
                request.setAttribute("msgcompName", msgcompName);
            }
            if (contName.equals("")) {
                msgcontName = "Contact name is required.";
                request.setAttribute("msgcontName", msgcontName);
            }
            if (contTitle.equals("")) {
                msgcontTitle = "Contact title is required.";
                request.setAttribute("msgcontTitle", msgcontTitle);
            }
            if (address.equals("")) {
                msgaddress = "Address is required.";
                request.setAttribute("msgaddress", msgaddress);
            }
            if (!msgcompName.equals("") || !msgcontName.equals("")
                    || !msgcontTitle.equals("") || !msgaddress.equals("")) {
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            } else {
                cus.setCustomerID(cusID);
                cus.setCompanyName(compName);
                cus.setContactName(contName);
                cus.setContactTitle(contTitle);
                cus.setAddress(address);
//            Customer c = new Customer(cusID, compName, contName, contTitle, address);
                checkCus = new CustomerDAO().createProfile(cus);
            }

        } else {
            Account acc = (Account) request.getSession().getAttribute("accSession");
            cus = new CustomerDAO().getCustomerById(acc.getCustomerID());
        }

        if (cus != null) {
            //CART
            Cart cart = null;
            if (request.getSession().getAttribute("cartSession") != null) {
                cart = (Cart) request.getSession().getAttribute("cartSession");
            } else {
                cart = new Cart();
            }

            //===required date===
            Date requiredDate;
            //---get current date---
            //get current time in milliseconds
            long millis = System.currentTimeMillis();
            // creating a new object of the class Date  
            java.sql.Date date = new java.sql.Date(millis);
            //set value
            Date datemin = this.addDays(date, 3);
            Date datemax = this.addDays(date, 30);
            request.setAttribute("datemin", datemin);
            request.setAttribute("datemax", datemax);
            //get value 
            try {
                String requiredDate_raw = request.getParameter("requiredDate");
                requiredDate = Date.valueOf(requiredDate_raw);
            } catch (Exception e) {
                requiredDate = datemax;
            }
            request.setAttribute("requiredDate", requiredDate);

            //ORDER
            OrderDAO oDAO = new OrderDAO();
            int res = oDAO.addOrder(cus, cart, requiredDate);

            request.getSession().removeAttribute("cartSession"); //remove cart
            request.getSession().setAttribute("size", 0); //remove size

            request.getRequestDispatcher("cart.jsp").forward(request, response);

        }
//        else {
//            response.sendRedirect("home");
//        }
    }

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    public static Date subtractDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -days);
        return new Date(c.getTimeInMillis());
    }

    private String randomString(int length) {
        Random random = new Random();

        String setOfCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String character = "";
        String randomCharacter = "";

        for (int i = 0; i < length; i++) {
            character = Character.toString(setOfCharacters.charAt(random.nextInt(setOfCharacters.length())));
            randomCharacter += character;
        }
        return randomCharacter;
    }
}
