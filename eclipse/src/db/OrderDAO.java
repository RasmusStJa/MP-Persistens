package db;

import model.SaleOrder;
import java.util.List;

public interface OrderDAO {
    int save(SaleOrder o) throws DataAccessException;
    SaleOrder findByOrderNo(int orderNo) throws DataAccessException;
    boolean updateDeliveryStatus(int orderNo, String status) throws DataAccessException;
//    List<SaleOrder> findByCustomerPhoneno(String phoneno) throws DataAccessException; potentielt set ikke relevant
}