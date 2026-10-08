package controller;


import db.ProductDAO;
import model.Price;
import model.Product;

public class ProductController {
    private ProductDAO productDAO;

 
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product findProduct(int productNumber) {
        return productDAO.findByProductNumber(productNumber);
    }
    
    public Price getCurrentPrice(Product product) {
    	return productDAO.findCurrentPriceByProductNumber(product.getProductNumber());
    }

    public boolean reserve(Product p, int qty) {
    	p.setReservedStock(p.getReservedStock() + qty);
        productDAO.updateReservedStock(p);
        return true;
    }
}