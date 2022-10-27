/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author phamt
 */
public class OrderDetail {

    private int orderID;
    private int productID;
    private long unitPrice;
    private int quantity;
    private long discount;
    private String productName;

    public OrderDetail(int OrderID, int ProductID, long UnitPrice, int Quantity, long Discount, String ProductName) {
        this.orderID = OrderID;
        this.productID = ProductID;
        this.unitPrice = UnitPrice;
        this.quantity = Quantity;
        this.discount = Discount;
        this.productName = ProductName;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getProductID() {
        return productID;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getDiscount() {
        return discount;
    }

    public String getProductName() {
        return productName;
    }
    
      public long getAmmount() {
        return unitPrice*quantity;
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
