package db;

import model.Product;

public interface ProductDAO {
    Product findByProductNumber(int productNumber);
    void updateReservedQty(Product p);
    
}