package controllers;

import dal.Account;
import dal.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import models.CustomerDAO;

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
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("accSession") == null) {
            String compName = request.getParameter("compName");
            String contName = request.getParameter("contName");
            String contTitle = request.getParameter("contTitle");
            String address = request.getParameter("address");
            String cusID = randomString(5);
            Customer c = new Customer(cusID, compName, contName, contTitle, address);
//        Customer cus = new CustomerDAO().createProfile(c);
        }
    }
}
