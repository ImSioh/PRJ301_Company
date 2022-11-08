package controllers;

import dal.Category;
import dal.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import models.CategoryDAO;
import models.ProductDAO;

public class ProductList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Product> pList = new ArrayList<>();
        ProductDAO pd = new ProductDAO();
        String keyword = request.getParameter("txtSearch");
        int page = 0;
        int elements = 10;
        int numberOfPage;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
            page = 1;
        }

//        || keyword.trim().equals("")
        
        if (keyword == null) {
            pList = pd.getProductsByPage(page, elements);
            numberOfPage = (int) Math.ceil(pd.getProduct().size() / elements);

        } else {
            keyword = keyword.trim();

            HashMap<String, String> filters = new HashMap<>();
            filters.put("CategoryID", request.getParameter("ddlCategory"));

            pList = pd.getProductByKeywordPaging(keyword, filters, page, elements);
            numberOfPage = (int) Math.ceil(pd.getProductByKeyword(keyword, filters).size() / elements);

        }

        ArrayList<Category> c = new CategoryDAO().getCategory();

        request.setAttribute("page", page);
        request.setAttribute("numberOfPage", numberOfPage);
        request.setAttribute("product", pList);
        request.setAttribute("category", c);

        if (request.getSession().getAttribute("accSession") != null) {
            request.getRequestDispatcher("product-list.jsp").forward(request, response);
        } else {
            response.sendRedirect("product-list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
