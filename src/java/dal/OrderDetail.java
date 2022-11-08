/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

public class OrderDetail {

    private int orderID;
    private int productID;
    private double unitPrice;
    private int quantity;
    private double discount;
    private String productName;

    public OrderDetail() {
    }

    public OrderDetail(int orderID, int productID, double unitPrice, int quantity, double discount) {
        this.orderID = orderID;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public OrderDetail(int productID, double unitPrice, int quantity) {
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderDetail(int productID, double unitPrice, int quantity, String productName) {
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.productName = productName;
    }

    public OrderDetail(int orderID, int productID, double unitPrice, int quantity, double discount, String productName) {
        this.orderID = orderID;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.productName = productName;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public String getProductName() {
        return productName;
    }

    public double getAmmount() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OrderDetail{");
        sb.append("OrderID=").append(orderID);
        sb.append(", ProductID=").append(productID);
        sb.append(", UnitPrice=").append(unitPrice);
        sb.append(", Quantity=").append(quantity);
        sb.append(", Discount=").append(discount);
        sb.append(", ProductName=").append(productName);
        sb.append('}');
        return sb.toString();
    }

}
