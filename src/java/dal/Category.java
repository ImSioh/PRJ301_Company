package dal;

public class Category {

    private int categoryID;
    private String categoryName;
    private String description;
//    private int picture;

    public Category(String CategoryName) {
        this.categoryName = CategoryName;
    }

    public Category(int CategoryID, String CategoryName, String Description) {
        this.categoryID = CategoryID;
        this.categoryName = CategoryName;
        this.description = Description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.categoryID = CategoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.categoryName = CategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    @Override
    public String toString() {
        return "Category{" + "CategoryID=" + categoryID + ", CategoryName=" + categoryName + ", Description=" + description + '}';
    }
}
