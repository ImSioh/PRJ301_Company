package controllers;

import dal.Category;
import dal.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.CategoryDAO;
import models.ProductDAO;

public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Product> pHot = new ProductDAO().getProductHot();
        ArrayList<Product> pBest = new ProductDAO().getProductBestSale();
        ArrayList<Product> pNew = new ProductDAO().getProductNew();
        ArrayList<Category> c = new CategoryDAO().getCategory();

        request.setAttribute("productHot", pHot);
        request.setAttribute("productBestSale", pBest);
        request.setAttribute("productNew", pNew);
        request.setAttribute("category", c);
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
