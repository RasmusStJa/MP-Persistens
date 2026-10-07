package model;

public class Product {
    
    private int productNumber;
    private String name;
    private double price;
    private int availableQty;
    private int reservedQty;

    
    public Product(int productNumber, String name, double price, int availableQty) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.availableQty = availableQty;
        this.reservedQty = 0;
    }

   
    public int getProductNumber() { 
    	return productNumber; }
    
    public String getName() {
    	return name; }
    
    public double getPrice() {
    	return price; }
    
    public int getAvailableQty() {
    	return availableQty; }
    
    public int getReservedQty() {
    	return reservedQty; }
    
    public void setReservedQty(int q) {
        this.reservedQty = q;
        this.availableQty = this.availableQty - q;
    }
}