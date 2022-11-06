/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAO extends DBContext {

    public ArrayList<OrderDetail> getOrderDetailsByOrderID(int OrderID) {
        ArrayList<OrderDetail> ods = new ArrayList<>();
        try {

            String sql = "select od.*, p.ProductName from \n"
                    + "[Order Details] od inner join Products p\n"
                    + "on od.ProductID = p.ProductID\n"
                    + "where OrderID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, OrderID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                String ProductName = rs.getString("ProductName");

                OrderDetail od = new OrderDetail(ProductID, ProductID, UnitPrice, Quantity, Discount, ProductName);
                ods.add(od);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ods;
    }

    public static void main(String[] args) {
        ArrayList<OrderDetail> ods = new OrderDetailDAO().getOrderDetailsByOrderID(11072);
        for (OrderDetail od : ods) {
            System.out.println(od);
        }
    }
}
