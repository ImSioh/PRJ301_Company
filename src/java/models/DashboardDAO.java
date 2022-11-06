/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Orders;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author phamt
 */
public class DashboardDAO extends DBContext {

    public List getSalesByMonth() {
        ArrayList<Integer> sales = new ArrayList<>();
        ArrayList<String> months = new ArrayList<>();
        try {
            String sql = "select Round(Sum((UnitPrice * Quantity) * (1 - Discount)), 0) as TotalSales,  \n"
                    + "		FORMAT (ShippedDate, 'yyyy-MM') as Month\n"
                    + "		\n"
                    + "from [Order Details] od\n"
                    + "inner join Orders o\n"
                    + "on od.OrderID = o.OrderID\n"
                    + "where ShippedDate is not null\n"
                    + "group by FORMAT (ShippedDate, 'yyyy-MM')";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int TotalSales = rs.getInt("TotalSales");
                String Month = rs.getString("Month");

                sales.add(TotalSales);
                months.add(Month);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return Arrays.asList(sales, months);
    }
}
