/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import models.AccountDAO;

public class AccountSignUp extends HttpServlet {

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
        if (request.getSession().getAttribute("accSession") == null) {
            //Chuyen tiep yeu cau cua nguoi dung sang 'signup.jsp'
            request.getRequestDispatcher("../signup.jsp").forward(request, response);
        } else {
            response.sendRedirect("../home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Lay du lieu tu 'signup' form chuyen len
        String CustomerID = randomString(5);
        String CompanyName = request.getParameter("copaName");
        String ContactName = request.getParameter("contName");
        String ContactTitle = request.getParameter("contTitle");
        String Address = request.getParameter("Addr");

        String Email = request.getParameter("Email");
        String Password = request.getParameter("Pass");
        String RePassword = request.getParameter("rePass");

        String msgCPN = "";
        String msgCTN = "";
        String msgCTT = "";
        String msgADR = "";
        String msgE = "";
        String msgP = "";
        String msgRP = "";

        if (CompanyName.equals("")) {
            msgCPN = "Company name is required";
            request.setAttribute("msgCPN", msgCPN);
        }
        if (ContactName.equals("")) {
            msgCTN = "Contact name is required";
            request.setAttribute("msgCTN", msgCTN);
        }
        if (ContactTitle.equals("")) {
            msgCTT = "Contact title is required";
            request.setAttribute("msgCTT", msgCTT);
        }
        if (Address.equals("")) {
            msgADR = "Address is required";
            request.setAttribute("msgADR", msgADR);
        }
        if (Email.equals("")) {
            msgE = "Email is required";
            request.setAttribute("msgE", msgE);
        }
        if (Password.equals("")) {
            msgP = "Password is required";
            request.setAttribute("msgP", msgP);
        }
        if (RePassword.equals("")) {
            msgRP = "RePassword is required";
            request.setAttribute("msgRP", msgRP);
        }

        if (!msgE.equals("") || !msgP.equals("") || !msgRP.equals("") || !msgADR.equals("")
                || !msgCTT.equals("") || !msgCTN.equals("") || !msgCPN.equals("")) {
            request.getRequestDispatcher("../signup.jsp").forward(request, response);
        } else {
            Account acc = new Account(0, Email, Password, CustomerID, 0, 2);
            Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address);

            if (new AccountDAO().AddAccount(acc, cus) != 0) {
                response.sendRedirect("signin");
            } else {
                request.getRequestDispatcher("signup");
            }
        }
    }
}
