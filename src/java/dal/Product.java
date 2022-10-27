package dal;

public class Product {

    private int productID;
    private String productName;
    private int categoryID;
    private String quantityPerUnit;
    private double unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;
    private boolean discontinued;

    public Product(int ProductID, String ProductName, int CategoryID, String QuantityPerUnit, double UnitPrice, int UnitsInStock, int UnitsOnOrder, int ReorderLevel, boolean Discontinued) {
        this.productID = ProductID;
        this.productName = ProductName;
        this.categoryID = CategoryID;
        this.quantityPerUnit = QuantityPerUnit;
        this.unitPrice = UnitPrice;
        this.unitsInStock = UnitsInStock;
        this.unitsOnOrder = UnitsOnOrder;
        this.reorderLevel = ReorderLevel;
        this.discontinued = Discontinued;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int ProductID) {
        this.productID = ProductID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String ProductName) {
        this.productName = ProductName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.categoryID = CategoryID;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String QuantityPerUnit) {
        this.quantityPerUnit = QuantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.unitPrice = UnitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int UnitsInStock) {
        this.unitsInStock = UnitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int UnitsOnOrder) {
        this.unitsOnOrder = UnitsOnOrder;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int ReorderLevel) {
        this.reorderLevel = ReorderLevel;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean Discontinued) {
        this.discontinued = Discontinued;
    }

    @Override
    public String toString() {
        return "Product{" + "ProductID=" + productID + ", ProductName=" + productName + ", CategoryID=" + categoryID + ", QuantityPerUnit=" + quantityPerUnit + ", UnitPrice=" + unitPrice + ", UnitsInStock=" + unitsInStock + ", UnitsOnOrder=" + unitsOnOrder + ", ReorderLevel=" + reorderLevel + ", Discontinued=" + discontinued + '}';
    }
}
