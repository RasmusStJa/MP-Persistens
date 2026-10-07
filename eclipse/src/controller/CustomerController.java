package controller;

import model.Customer;

public class CustomerController {
    
    private CustomerDAO customerDAO;

   
    public CustomerController() {
        this.customerDAO = new CustomerDAO();
    }

   
    
    public Customer findCustomer(String phone, String email) {
        Customer c = customerDAO.findByPhoneOrEmail(phone, email);
        
   
        if (c == null) {
            c = new Customer("Ny Kunde", phone, email);
            customerDAO.save(c);
        }
        
        return c;
    }
}
