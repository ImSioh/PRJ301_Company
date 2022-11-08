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

public class OrderDAO extends DBContext {
    
    public ArrayList<Orders> getAllOrder() {
        ArrayList<Orders> order = new ArrayList<>();
        try {
            String sql = "select * from Orders\n"
                    + "order by OrderDate desc\n";
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
    
    public ArrayList<Orders> getAllOrderKeyword(String keyword) {
        ArrayList<Orders> order = new ArrayList<>();
        try {
            String sql = "select * from Orders\n"
                    + "where OrderID like ?\n"
                    + "order by OrderDate desc\n";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
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
    
    public int cancelOrder(int OrderId) {
        
        int rs = -1;
        try {
            String sql = "update Orders\n"
                    + "set RequiredDate = null\n"
                    + "where OrderID = ? and ShippedDate is null";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, OrderId);
            
            rs = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public ArrayList<Orders> getOrdersByPage(int page, int elements) {
        ArrayList<Orders> order = new ArrayList<>();
        int start = page * elements - elements;
        try {
            String sql = "select * from Orders\n"
                    + "order by OrderDate desc\n"
                    + "offset ? rows\n"
                    + "fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, elements);
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
        } catch (Exception e) {
        }
        return order;
    }
    
    public int addOrder(Customer cus, Cart cart, Date requiredDate) {
        int res1 = 0;
        int res3 = 0;
        try {
            //add vao bang [Orders]
            String sql1 = "insert into Orders(CustomerID, OrderDate, RequiredDate, Freight, ShipCity) "
                    + "values (?,GETDATE(),?,?,?)";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            
            ps1.setString(1, cus.getCustomerID());
            ps1.setDate(2, requiredDate);
            ps1.setDouble(3, 0);
            ps1.setString(4, cus.getAddress());
            
            res1 = ps1.executeUpdate();

            //lay ra OrderID vua add vao
            String sql2 = "select top 1 OrderID from Orders "
                    + "order by OrderID desc";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ResultSet rs = ps2.executeQuery();

            //add OrderID vao 
            while (rs.next()) {
                int OrderID = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql3 = "insert into [Order Details] "
                            + "values(?,?,?,?,?)";
                    PreparedStatement ps3 = connection.prepareStatement(sql3);
                    
                    ps3.setInt(1, OrderID);
                    ps3.setInt(2, i.getProduct().getProductID());
                    ps3.setDouble(3, i.getProduct().getUnitPrice());
                    ps3.setInt(4, i.getQuantity());
                    ps3.setDouble(5, 0);
                    res3 = ps3.executeUpdate();
                    cart.removeItem(OrderID);
                }
            }
        } catch (Exception e) {
            
        }
        if (res1 != 0 && res3 != 0) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        ArrayList<Orders> order = new OrderDAO().getAllOrderKeyword("12");
        for (Orders orders : order) {
            System.out.println(orders);
        }
        
        System.out.println("==============================================================");
//        int rs = new OrderDAO().cancelOrder(11072, "ERNSH");
//
//        ArrayList<Orders> orders = new OrderDAO().getOrdersByCustomerID("ERNSH", "all");
//        for (Orders o : orders) {
//            System.out.println(o);
//        }
//        ArrayList<Orders> order2 = new OrderDAO().getOrdersByPage(1, 10);
//        for (Orders orders : order2) {
//            System.out.println(orders);
//        }

        ArrayList<Orders> order3 = new OrderDAO().getAllOrder();
        System.out.println("size = " + order3.size());
    }
}
