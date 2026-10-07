package model;

class Product {
    int productNumber;
    String name;
    double price;
    int availableQty;
    int reservedQty;

    public Product(int productNumber, String name, double price, int availableQty) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.availableQty = availableQty;
        this.reservedQty = 0;
    }

    public void reserve(int qty) {
        this.reservedQty += qty;
        this.availableQty -= qty;
    }
}