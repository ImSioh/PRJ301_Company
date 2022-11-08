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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO extends DBContext {

    public ArrayList<Orders> parseResultSetToArray(ResultSet rs) {
        ArrayList<Orders> order = new ArrayList<>();
        try {
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
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
    
    public int getNumOrders() {
        int numOrder = -1;
        try {
            String sql = "select COUNT(*) from Orders\n";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            numOrder = rs.getInt(1);
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return numOrder;
    }   
    
    public ArrayList<Orders> getAllOrder() {
        ArrayList<Orders> order = new ArrayList<>();
        try {
            String sql = "select * from Orders\n"
                    + "order by OrderDate desc\n";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            order = parseResultSetToArray(rs);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return order;
    }

    public Orders getOrderById(String OrderId) {
        Orders o = null;
        try {
            String sql = "select * from Orders\n"
                    + "where OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, OrderId);
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

                o = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return o;
    }

    public ArrayList<Orders> getAllOrderKeyword(String keyword, HashMap<String, String> filter) {
        ArrayList<Orders> order = new ArrayList<>();
        int sql_param_counter = 1;
        try {
            String sql = "select * from Orders\n"
                    + "where OrderID like ?";

            String StartOrderDate = filter.get("StartOrderDate");
            String EndOrderDate = filter.get("EndOrderDate");

            if (StartOrderDate != null) {
                if (!"".equals(StartOrderDate.trim())) {
                    sql += " and ? <= OrderDate";
                }
            }

            if (EndOrderDate != null) {
                if (!"".equals(EndOrderDate.trim())) {
                    sql += " and OrderDate <= ?";
                }
            }

            sql += "\n";
            sql += "order by OrderDate desc\n";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(sql_param_counter, "%" + keyword + "%");

            sql_param_counter += 1;

            if (StartOrderDate != null) {
                if (!"".equals(StartOrderDate.trim())) {
                    ps.setString(sql_param_counter, StartOrderDate);
                    sql_param_counter += 1;
                }
            }

            if (EndOrderDate != null) {
                if (!"".equals(EndOrderDate.trim())) {
                    ps.setString(sql_param_counter, EndOrderDate);
                    sql_param_counter += 1;
                }
            }

//            System.out.println(ps.toString());
//            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            order = parseResultSetToArray(rs);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return order;
    }

    public ArrayList<Orders> getAllOrderKeywordPaging(String keyword, HashMap<String, String> filter, int page, int elements) {
        ArrayList<Orders> order = new ArrayList<>();
        int start = page * elements - elements;

        String StartOrderDate = filter.get("StartOrderDate");
        if (StartOrderDate == null || "".equals(StartOrderDate.trim())) {
            StartOrderDate = "1900-01-01";
        }
        String EndOrderDate = filter.get("EndOrderDate");
        if (EndOrderDate == null || "".equals(EndOrderDate.trim())) {
            EndOrderDate = "2100-01-01";
        }

        try {
            String sql = "select * from Orders\n"
                    + "where OrderID like ? \n"
                    + "and (OrderDate between ? and ?)\n"
                    + "order by OrderDate desc\n"
                    + "offset ? rows\n"
                    + "fetch next ? rows only";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, StartOrderDate);
            ps.setString(3, EndOrderDate);
            ps.setInt(4, start);
            ps.setInt(5, elements);

            ResultSet rs = ps.executeQuery();

            order = parseResultSetToArray(rs);
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
            order = parseResultSetToArray(rs);
        } catch (Exception e) {
        }
        return order;
    }

    public static void main(String[] args) {
        HashMap<String, String> filters = new HashMap<>();
//        filters.put("StartOrderDate", "1997-01-01");
//        filters.put("EndOrderDate", "1999-01-01");

        ArrayList<Orders> order = new OrderDAO().getAllOrderKeywordPaging("2", filters, 2, 3);
        for (Orders orders : order) {
            System.out.println(orders);
        }

    }
}
