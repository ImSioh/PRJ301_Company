package dal;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    private Item getItemById(int id) {
        for (Item i : items) {
            if (i.getProduct().getProductID() == id) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }

    //them item vao cart
    public void addItem(Item t) {
        //neu da co item trong cart
        if (getItemById(t.getProduct().getProductID()) != null) {
            Item i = getItemById(t.getProduct().getProductID());
            i.setQuantity(i.getQuantity() + t.getQuantity());
        } //neu chua co item trong cart
        else {
            items.add(t);
        }
    }

    public void removeItem(int id) {
        if (getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }

    public double getTotalMoney() {
        double t = 0;
        for (Item i : items) {
            t += (i.getQuantity() * i.getProduct().getUnitPrice());
        }
        return t;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}