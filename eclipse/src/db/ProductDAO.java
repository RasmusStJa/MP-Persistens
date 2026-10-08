package db;

import model.Product;
import model.Price;
//import java.util.List;

public interface ProductDAO {
    Product findByProductNumber(int productNumber) throws DataAccessException;
    Price findCurrentPriceByProductNumber(int productNumber) throws DataAccessException; 
    boolean updateReservedStock(Product p) throws DataAccessException;
//    List<Product> findByProductNumberByList(int productNumber); ikke relevant medmindre vi vil implementere det som reel funktionalitet
    
}