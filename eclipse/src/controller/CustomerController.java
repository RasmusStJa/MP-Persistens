package controller;


import db.CustomerDAO;
import model.Customer;

public class CustomerController {
    private CustomerDAO customerDAO;
    
    
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer findCustomer(String phone, String email) {
        return customerDAO.findByPhoneOrEmail(phone, email);
    }
}
