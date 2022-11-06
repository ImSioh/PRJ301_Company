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
        try {
            int page = 0;
            int elements = 10;
            int numberOfPage;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (Exception e) {
                page = 1;
            }
            // check sau khi insert neu khong phai page 1 thi se khong in ra cai product vua add vao 
            if (page != 1) {
                Product pNew = null;
                request.getSession().setAttribute("productNew", pNew);
            }
            request.setAttribute("page", page);

            ProductDAO pd = new ProductDAO();
            ArrayList<Product> pList = new ArrayList<>();
            String keyword = request.getParameter("txtSearch");
            
            if (keyword == null || keyword.trim().equals("")) {
                pList = pd.getProductsByPage(page, elements);
            } else {
                pList = pd.getProductByKeyword(keyword);
            }
            
            request.setAttribute("product", pList);

            numberOfPage = pd.getProduct().size() % elements == 0 ? pd.getProduct().size() / elements : pd.getProduct().size() / elements + 1;
            request.setAttribute("numberOfPage", numberOfPage);

            ArrayList<Category> c = new CategoryDAO().getCategory();
            request.setAttribute("category", c);

            if (request.getSession().getAttribute("accSession") != null) {
                request.getRequestDispatcher("product-list.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
