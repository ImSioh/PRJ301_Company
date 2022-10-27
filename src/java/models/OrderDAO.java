/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class OrderDAO extends DBContext {

    public ArrayList<Orders> getAllOrder() {
        ArrayList<Orders> order = new ArrayList<>();
        try {
            String sql = "select * from Orders";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Orders o = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);

                order.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return order;
    }

    public ArrayList<Orders> getOrdersByCustomerID(String CustomerID, String subset) {
        ArrayList<Orders> orders = new ArrayList<>();
        try {

            String sql = null;
            if (subset.equals("all")) {
                sql = "select *,\n"
                        + "CASE\n"
                        + "    WHEN ShippedDate is null THEN 'Pending'\n"
                        + "    ELSE 'Complete'\n"
                        + "END AS Status\n"
                        + "from Orders o\n"
                        + "Where o.CustomerID = ? and RequiredDate is not null\n"
                        + "order by status desc, o.OrderID desc";
            } else if (subset.equals("cancel")) {
                sql = "select *,\n"
                        + "CASE\n"
                        + "    WHEN ShippedDate is null THEN 'Pending'\n"
                        + "    ELSE 'Complete'\n"
                        + "END AS Status\n"
                        + "from Orders o\n"
                        + "Where o.CustomerID = ? and RequiredDate is null\n"
                        + "order by status desc, o.OrderID desc";
            }

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, CustomerID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
//                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");

                Orders o = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate);

                orders.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orders;
    }

    public int cancelOrder(int OrderId, String CustomerId) {

        int rs = -1;
        try {
            String sql = "update Orders\n"
                    + "set RequiredDate = null\n"
                    + "where CustomerID = ? and OrderID = ? and ShippedDate is null";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, CustomerId);
            ps.setInt(2, OrderId);

            rs = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public static void main(String[] args) {
        ArrayList<Orders> order = new OrderDAO().getAllOrder();
        for (Orders orders : order) {
            System.out.println(orders);
        }

        System.out.println("==============================================================");
        int rs = new OrderDAO().cancelOrder(11072, "ERNSH");

        ArrayList<Orders> orders = new OrderDAO().getOrdersByCustomerID("ERNSH", "all");
        for (Orders o : orders) {
            System.out.println(o);
        }
    }
}
