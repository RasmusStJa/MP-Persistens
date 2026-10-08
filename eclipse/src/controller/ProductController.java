package controller;


import db.ProductDAO;
import model.Product;

public class ProductController {
    private ProductDAO productDAO;

 
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product findProduct(int productNumber) {
        return productDAO.findByProductNumber(productNumber);
    }

    public boolean reserve(Product p, int qty) {
        p.setReservedStock(qty);
        productDAO.updateReservedQty(p);
        return true;
    }
}