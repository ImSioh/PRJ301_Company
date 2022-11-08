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
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ProductID = Integer.parseInt(request.getParameter("txtProductID"));
        String ProductName = request.getParameter("txtProductName");
        String QuantityPerUnit = request.getParameter("txtQuantityPerUnit");
        int UnitsOnOrder = Integer.parseInt(request.getParameter("txtUnitsOnOrder"));
        boolean Discontinued = Boolean.parseBoolean(request.getParameter("chkDiscontinued"));

        //raw to validate
        String UnitPrice_raw = request.getParameter("txtUnitPrice");
        String ReorderLevel_raw = request.getParameter("txtReorderLevel");
        String UnitsInStock_raw = request.getParameter("txtUnitsInStock");
        String Category_raw = request.getParameter("ddlCategory");

        String msgProductName = "";
        String msgUnitsInStock = "";
        String msgCategory = "";
        String msgQuantityPerUnit = "";
        String msgUnitPrice = "";
        String msgReorderLevel = "";

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
        if (QuantityPerUnit.equals("")) {
            msgQuantityPerUnit = "QuantityPerUnit is required.";
            request.setAttribute("msgQuantityPerUnit", msgQuantityPerUnit);
        }
        if (UnitPrice_raw.equals("")) {
            msgUnitPrice = "UnitPrice is required.";
            request.setAttribute("msgUnitPrice", msgUnitPrice);
        }
        if (ReorderLevel_raw.equals("")) {
            msgReorderLevel = "ReorderLevel is required.";
            request.setAttribute("msgReorderLevel", msgReorderLevel);
        }

        if (!msgProductName.equals("") || !msgUnitsInStock.equals("") || !msgCategory.equals("") || !msgUnitsInStock.equals("")
                || !msgQuantityPerUnit.equals("") || !msgUnitPrice.equals("") || !msgReorderLevel.equals("")) {
            Product p = new ProductDAO().getProductById(ProductID);
            request.setAttribute("product", p);
            ArrayList<Category> c = new CategoryDAO().getCategory();
            request.setAttribute("category", c);
            request.getRequestDispatcher("product-edit.jsp").forward(request, response);
        } else {
            //get Int input of CateID & UnitsStock
            int CategoryID = Integer.parseInt(Category_raw);
            int UnitsInStock = Integer.parseInt(UnitsInStock_raw);
            double UnitPrice = Double.parseDouble(UnitPrice_raw);
            int ReorderLevel = Integer.parseInt(ReorderLevel_raw);

            Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

            if (new ProductDAO().editProduct(p) != 0) {
                response.sendRedirect("product-list");
            } else {
                request.getRequestDispatcher("product-edit");
            }
        }
    }
}
