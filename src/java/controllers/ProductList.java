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

public class ProductList extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Product> pList = new ArrayList<>();
        String keyword = request.getParameter("txtSearch");
        if (keyword == null || keyword.trim().equals("")) {
            pList = new ProductDAO().getProduct();
            
        } else {
            pList = new ProductDAO().getProductByKeyword(keyword);
            
        }
        ArrayList<Category> c = new CategoryDAO().getCategory();
        
        request.setAttribute("product", pList);
        request.setAttribute("category", c);
        
        if (request.getSession().getAttribute("AccSession") != null) {
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } else {
            response.sendRedirect("product-list");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
