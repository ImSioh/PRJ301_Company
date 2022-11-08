package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import dal.*;
import jakarta.servlet.http.Cookie;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import models.*;

public class CartProfileCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //CUSTOMER
        //neu co session thi set info acc va cus,
        //neu khong co session thi k set info
        if (request.getSession().getAttribute("accSession") != null) {
            Account acc = (Account) request.getSession().getAttribute("accSession");
            String cID = acc.getCustomerID();
            Customer cus = new CustomerDAO().getCustomerById(cID);

            //set thuoc tinh sang jsp
            request.setAttribute("customer", cus);
            request.setAttribute("acc", acc);
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

        //CART
        Cart cart = null;
        //neu co cart roi thi lay ra cart da co
        if (request.getSession().getAttribute("cartSession") != null) {
            cart = (Cart) request.getSession().getAttribute("cartSession");
        } //neu chua co cart thi tao moi cart
        else {
            cart = new Cart();
        }
        List<Item> list = cart.getItems();
        int size = !list.isEmpty() ? list.size() : 0;

        request.getSession().setAttribute("cartSession", cart);
        request.getSession().setAttribute("totalMoney", cart.getTotalMoney());
        request.getSession().setAttribute("size", size);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Thao tac voi customer
        //tao doi tuong customer
        Customer cus = new Customer();
        //neu customer chua co tai khoan thi add thong tin mua hang
        if (request.getSession().getAttribute("accSession") == null) {
            String compName = request.getParameter("compName");
            String contName = request.getParameter("contName");
            String contTitle = request.getParameter("contTitle");
            String address = request.getParameter("address");
            String cusID = randomString(5);
            cus.setCustomerID(cusID);
            cus.setCompanyName(compName);
            cus.setContactName(contName);
            cus.setContactTitle(contTitle);
            cus.setAddress(address);
//            Customer c = new Customer(cusID, compName, contName, contTitle, address);
            int res = new CustomerDAO().createProfile(cus);
        } else {
            Account acc = (Account) request.getSession().getAttribute("accSession");
            cus = new CustomerDAO().getCustomerById(acc.getCustomerID());
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
}
