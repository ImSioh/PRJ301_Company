/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.Category;
import dal.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import models.CategoryDAO;
import models.ProductDAO;

public class Search extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        String keyword = req.getParameter("keyword").trim();
        int page = 0;
        int elements = 12;
        int numberOfPage;
        ProductDAO pd = new ProductDAO();
        
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }
        
        HashMap<String, String> filters = new HashMap<>();
        filters.put("CategoryID", req.getParameter("ddlCategory"));

        ArrayList<Product> ps = pd.getProductByKeywordPaging(keyword, filters, page, elements);
        numberOfPage = (int) Math.ceil(pd.getProductByKeyword(keyword, filters).size() / elements);               
        ArrayList<Category> c = new CategoryDAO().getCategory();
        
        
        
        req.setAttribute("page", page);
        req.setAttribute("numberOfPage", numberOfPage);
        req.setAttribute("category", c);
        req.setAttribute("product_list", ps);
        
        req.getRequestDispatcher("user-search.jsp").forward(req, resp);
//        resp.getWriter().print(keyword);

    }
    
}
