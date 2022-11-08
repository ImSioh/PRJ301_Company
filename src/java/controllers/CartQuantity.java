/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.Cart;
import dal.Item;
import dal.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.ProductDAO;

public class CartQuantity extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartQuantity</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartQuantity at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cart cart = null;
        if (request.getSession().getAttribute("cartSession") != null) {
            cart = (Cart) request.getSession().getAttribute("cartSession");
        } else {
            cart = new Cart();
        }
        int num = Integer.parseInt(request.getParameter("num"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        if (num == -1 && cart.getQuantityById(pid) <= 1) {
            cart.removeItem(pid);
        } else {
            ProductDAO pd = new ProductDAO();
            Product p = pd.getProductById(pid);
            double discount = 1;
            double price = p.getUnitPrice() * discount;
            Item t = new Item(p, num, price);
            cart.addItem(t);
        }
        List<Item> list = cart.getItems();
        request.getSession().setAttribute("totalMoney", cart.getTotalMoney());
        request.getSession().setAttribute("cartSession", cart);
        request.getSession().setAttribute("size", list.size());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cart cart = null;
        if (request.getSession().getAttribute("cartSession") != null) {
            cart = (Cart) request.getSession().getAttribute("cartSession");
        } else {
            cart = new Cart();
        }
        int pid = Integer.parseInt(request.getParameter("pid"));
        cart.removeItem(pid);
        List<Item> list = cart.getItems();
        request.getSession().setAttribute("totalMoney", cart.getTotalMoney());
        request.getSession().setAttribute("cartSession", cart);
        request.getSession().setAttribute("size", list.size());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
