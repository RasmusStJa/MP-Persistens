package db;

import model.SaleOrder;

public interface OrderDAO {
    int save(SaleOrder o);
    
}