package db;

import model.Customer;

public interface CustomerDAO {
    Customer findByPhoneOrEmail(String phone, String email);
}