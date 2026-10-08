package model;

public class OrderLineItem {
    private int quantity;
    private double unitPrice;
    private Product product;

    public OrderLineItem(Product p, int qty, int unitPrice) {
        product = p;
        quantity = qty;
        this.unitPrice = unitPrice; 
    }
 
    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public double getSubtotal() {
        return getQuantity() * getUnitPrice();
    }
}