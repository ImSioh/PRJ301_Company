/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

public class ProductListByCategoryId extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int CategoryID = Integer.parseInt(request.getParameter("id"));

//        ArrayList<Category> catList = new CategoryDAO().getCategoryById(CategoryID);
//
//        request.setAttribute("category", catList);
        
        ArrayList<Product> pList = new ProductDAO().getProductByCategoryId(CategoryID);
        ArrayList<Category> c = new CategoryDAO().getCategory();

        request.setAttribute("product", pList);
        request.setAttribute("category", c);
        
        request.getRequestDispatcher("product-by-category-id.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
