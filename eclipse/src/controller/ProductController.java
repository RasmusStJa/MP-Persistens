package controller;

import model.Product;

public class ProductController {
    

    private ProductDAO productDAO;

  
    public ProductController() {
        this.productDAO = new ProductDAO();
    }

    
    public Product findProduct(int productNumber) {
        return productDAO.findByProductNumber(productNumber);
    }

    public boolean reserve(Product p, int qty) {
        p.setReservedQty(qty);
        
        
        productDAO.updateReservedQty(p);
        
        return true; 
    }
}
