package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import dal.*;
import jakarta.servlet.http.Cookie;
import java.util.ArrayList;
import models.*;

public class CartServlet extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("accSession") != null) {
            Account acc = (Account) request.getSession().getAttribute("accSession");
            String cID = acc.getCustomerID();
            Customer cus = new CustomerDAO().getCustomerById(cID);

            request.setAttribute("customer", cus);
            request.setAttribute("acc", acc);
        }
        ArrayList<Product> p = new ProductDAO().getProduct();

        request.setAttribute("product", p);

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
//            if (res != 0) {
//                //xoa cookie
////                response.sendRedirect("signin");
//            } else {
//                //giu cookie
//                //yeu cau nhap lai thong tin customer
////                request.getRequestDispatcher("signup");
//            }
        } else {
            Account acc = (Account) request.getSession().getAttribute("accSession");
            cus = new CustomerDAO().getCustomerById(acc.getCustomerID());
        }

        //Thao tac voi cart
        //lay danh sach cookies
        Cookie[] arr = request.getCookies();
        //lay tham so truyen vao

        int productID;
        double unitPrice;
        int quantity;
        String productName;
        
        productID = Integer.parseInt(request.getParameter("productID"));
        unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        quantity = Integer.parseInt(request.getParameter("quantity"));
        productName = request.getParameter("productName");

    }
}
