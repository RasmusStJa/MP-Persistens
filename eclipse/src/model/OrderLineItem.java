package model;

public class OrderLineItem {
    

    private int quantity;
    private double unitPrice;
    private Product product;

    public OrderLineItem(Product p, int qty) {
        this.product = p;
        this.quantity = qty;
        this.unitPrice = p.getPrice(); 
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
        return quantity * unitPrice;
    }
}