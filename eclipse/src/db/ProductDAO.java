package db;

import model.Product;
import model.Price;
import java.util.List;

public interface ProductDAO {
    Product findByProductNumber(int productNumber);
    Price findCurrentPriceByProductNumber(int productNumber);
    boolean updateReservedStock(Product p);
    List<Product> findByProductNumberByList(int productNumber);
    
}