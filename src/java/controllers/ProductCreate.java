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
import models.CategoryDAO;
import models.ProductDAO;

public class ProductCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Product> pList = new ProductDAO().getProduct();
        ArrayList<Category> c = new CategoryDAO().getCategory();

        req.setAttribute("product", pList);
        req.setAttribute("category", c);

        if (req.getSession().getAttribute("accSession") != null) {
            req.getRequestDispatcher("product-create.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ProductName = req.getParameter("txtProductName");
        String QuantityPerUnit = req.getParameter("txtQuantityPerUnit");
        //int UnitsOnOrder = Integer.parseInt(req.getParameter("txtUnitsOnOrder"));
        boolean Discontinued = Boolean.parseBoolean(req.getParameter("chkDiscontinued"));

        //raw to validate
        String UnitPrice_raw = req.getParameter("txtUnitPrice");
        String ReorderLevel_raw = req.getParameter("txtReorderLevel");
        String UnitsInStock_raw = req.getParameter("txtUnitsInStock");
        String Category_raw = req.getParameter("ddlCategory");

        String msgProductName = "";
        String msgUnitsInStock = "";
        String msgCategory = "";
        String msgQuantityPerUnit = "";
        String msgUnitPrice = "";
        String msgReorderLevel = "";

        if (ProductName.equals("")) {
            msgProductName = "Product name is required.";
            req.setAttribute("msgProductName", msgProductName);
        }
        if (UnitsInStock_raw.equals("")) {
            msgUnitsInStock = "Units in stock is required.";
            req.setAttribute("msgUnitsInStock", msgUnitsInStock);
        }
        if (Category_raw.equals("")) {
            msgCategory = "Category is required.";
            req.setAttribute("msgCategory", msgCategory);
        }
        if (QuantityPerUnit.equals("")) {
            msgQuantityPerUnit = "QuantityPerUnit is required.";
            req.setAttribute("msgQuantityPerUnit", msgQuantityPerUnit);
        }
        if (UnitPrice_raw.equals("")) {
            msgUnitPrice = "UnitPrice is required.";
            req.setAttribute("msgUnitPrice", msgUnitPrice);
        }
        if (ReorderLevel_raw.equals("")) {
            msgReorderLevel = "ReorderLevel is required.";
            req.setAttribute("msgReorderLevel", msgReorderLevel);
        }

        if (!msgProductName.equals("") || !msgCategory.equals("") || !msgUnitsInStock.equals("")
                || !msgQuantityPerUnit.equals("") || !msgUnitPrice.equals("") || !msgReorderLevel.equals("")) {
            ArrayList<Category> c = new CategoryDAO().getCategory();
            req.setAttribute("category", c);
            req.getRequestDispatcher("product-create.jsp").forward(req, resp);
        } else {
            //get Int input of CateID & UnitsStock
            int CategoryID = Integer.parseInt(Category_raw);
            int UnitsInStock = Integer.parseInt(UnitsInStock_raw);
            double UnitPrice = Double.parseDouble(UnitPrice_raw);
            int ReorderLevel = Integer.parseInt(ReorderLevel_raw);

            Product p = new Product(0, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, 0, ReorderLevel, Discontinued);

            if (new ProductDAO().createProduct(p) != 0) {
                resp.sendRedirect("product-list");
            } else {
                req.getRequestDispatcher("product-create");
            }
        }
    }
}
