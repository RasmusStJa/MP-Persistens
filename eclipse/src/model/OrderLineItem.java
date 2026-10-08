package model;

public class OrderLineItem {
    private int quantity;
    private double unitPrice;
    private Product product;

    public OrderLineItem(int qty, Product p, double unitPrice) {
        product = p;
        quantity = qty;
        this.unitPrice = unitPrice; 
    }
    
    public OrderLineItem(int quantity, Product product) {
    	this.quantity = quantity;
    	this.product = product;
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