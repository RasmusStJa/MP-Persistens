package controller;


import db.CustomerDAO;
import db.DataAccessException;
import model.Customer;

public class CustomerController {
    private CustomerDAO customerDAO;
    
    
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer findCustomer(String phone) throws DataAccessException {
        return customerDAO.findByPhone(phone);
    }
}
