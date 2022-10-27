package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ProductDAO;

@WebServlet(name = "Delete2", urlPatterns = {"/delete"})
public class Delete2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ProductID = Integer.parseInt(request.getParameter("id"));
        ProductDAO pd = new ProductDAO();
        pd.deleteProductById(ProductID);

        response.sendRedirect("product-list");
    }
}
