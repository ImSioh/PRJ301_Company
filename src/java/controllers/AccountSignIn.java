/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.AccountDAO;

public class AccountSignIn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("accSession") != null) {
            request.getSession().removeAttribute("accSession");
            response.sendRedirect("../home");
        } else {
            //Chuyen tiep yeu cau cua nguoi dung sang 'signin.jsp'
            request.getRequestDispatcher("../signin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Lay du lieu tu 'signin' form chuyen len
        String email = request.getParameter("Email");
        String pass = request.getParameter("Pass");
        String msgEmail = "";
        String msgPass = "";

        if (email.equals("")) {
            msgEmail = "Email is required";
            request.setAttribute("msgEmail", msgEmail);
        }
        if (pass.equals("")) {
            msgPass = "Password is required";
            request.setAttribute("msgPass", msgPass);
        }

        //! ???
        if (!msgEmail.equals("") || !msgPass.equals("")) {
            request.getRequestDispatcher("../signin.jsp").forward(request, response);
        } else {
            Account acc = new AccountDAO().getAccount(email, pass);
            request.setAttribute("Account", acc);
            if (acc != null) {
                //Cấp session
                request.getSession().setAttribute("accSession", acc);
                //admin
                if (acc.getRole() == 1) {
                    response.sendRedirect(request.getContextPath() + "/dashboard");
                } //user
                else if (acc.getRole() == 2) {
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            } else {
                //Dong goi thong diep loi ve doGet (signin.jsp)
                request.setAttribute("msg", "This account does not exist!");
                request.getRequestDispatcher("../signin.jsp").forward(request, response);
            }
        }
    }
}
