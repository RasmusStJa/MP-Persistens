package db;

import model.Customer;

public interface CustomerDAO {
    Customer findByPhone(String phone) throws DataAccessException;
	void save(Customer c);
	
}