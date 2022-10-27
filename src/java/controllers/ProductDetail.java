package controllers;

import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import models.*;

public class ProductDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("pid"));
        Product pro = new ProductDAO().getProductById(pid);
        int cid = pro.getCategoryID();
        Category cat = new CategoryDAO().getCategoryById(cid);

        req.setAttribute("product", pro);
        req.setAttribute("category", cat);
        
        req.getRequestDispatcher("product-detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
