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

public class ProductEdit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int ProductID = Integer.parseInt(request.getParameter("id"));

        //Product
        ProductDAO pd = new ProductDAO();
        Product p = pd.getProductById(ProductID);
        request.setAttribute("product", p);

        //Category
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Category> c = cd.getCategory();
        request.setAttribute("category", c);
        if (request.getSession().getAttribute("accSession") != null) {
            request.getRequestDispatcher("product-edit.jsp").forward(request, response);
        } else {
            response.sendRedirect("product-list.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ProductID = Integer.parseInt(request.getParameter("txtProductID"));
        String ProductName = request.getParameter("txtProductName");
//        int CategoryID = Integer.parseInt(request.getParameter("ddlCategory"));
        String QuantityPerUnit = request.getParameter("txtQuantityPerUnit");
        double UnitPrice = Double.parseDouble(request.getParameter("txtUnitPrice"));
//        int UnitsInStock = Integer.parseInt(request.getParameter("txtUnitsInStock"));
        int UnitsOnOrder = Integer.parseInt(request.getParameter("txtUnitsOnOrder"));
        int ReorderLevel = Integer.parseInt(request.getParameter("txtReorderLevel"));
        boolean Discontinued = Boolean.parseBoolean(request.getParameter("chkDiscontinued"));

        //raw to validate
        String UnitsInStock_raw = request.getParameter("txtUnitsInStock");
        String Category_raw = request.getParameter("ddlCategory");

        String msgProductName = "";
        String msgUnitsInStock = "";
        String msgCategory = "";

        if (ProductName.equals("")) {
            msgProductName = "Product name is required.";
            request.setAttribute("msgProductName", msgProductName);
        }
        if (UnitsInStock_raw.equals("")) {
            msgUnitsInStock = "Units in stock is required.";
            request.setAttribute("msgUnitsInStock", msgUnitsInStock);
        }
        if (Category_raw.equals("")) {
            msgCategory = "Category is required.";
            request.setAttribute("msgCategory", msgCategory);
        }

        if (!msgProductName.equals("") || !msgCategory.equals("") || !msgUnitsInStock.equals("")) {
            Product p = new ProductDAO().getProductById(ProductID);
            request.setAttribute("product", p);
            ArrayList<Category> c = new CategoryDAO().getCategory();
            request.setAttribute("category", c);
            request.getRequestDispatcher("product-edit.jsp").forward(request, response);
        } else {
            //get Int input of CateID & UnitsStock
            int CategoryID = Integer.parseInt(request.getParameter("ddlCategory"));
            int UnitsInStock = Integer.parseInt(request.getParameter("txtUnitsInStock"));

            Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

            if (new ProductDAO().editProduct(p) != 0) {
                response.sendRedirect("product-list");
            } else {
                request.getRequestDispatcher("product-edit");
            }
        }
    }
}
