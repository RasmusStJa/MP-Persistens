package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import db.DataAccessException;
import db.OrderDAO;
import model.Customer;
import model.CustomerType;
import model.OrderLineItem;
import model.Price;
import model.Product;
import model.SaleOrder;

public class OrderController {
    private SaleOrder currentOrder;
    private CustomerController customerCtrl;
    private ProductController productCtrl;
    private OrderDAO orderDAO;
    private Product currentProduct;
    
    
    public OrderController(CustomerController cc, ProductController pc, OrderDAO dao) {
        this.customerCtrl = cc;
        this.productCtrl = pc;
        this.orderDAO = dao;
    }

    public SaleOrder createOrder() {
        this.currentOrder = new SaleOrder(LocalDate.now(), "OPEN", 0.0, new ArrayList<OrderLineItem>());
        return this.currentOrder;
    }

    public Customer enterCustomerInfo(String phone) throws DataAccessException {
        try {
          Customer customer = customerCtrl.findCustomer(phone);
          System.out.println("1. Kundesøgning færdig. Fundet kunde: " + (customer != null ? customer.getName() : "NULL"));

          if (customer != null && this.currentOrder != null) {
            this.currentOrder.setCustomer(customer);
            System.out.println("2. Kunde sat på ordre.");
            if (customer.getType() == CustomerType.CLUB) {
              this.currentOrder.setDiscount(10.0);
              System.out.println("3. Rabat sat.");
            }
          }
          return customer;
        } catch (Exception e) {
          System.err.println("❌ FEJL I enterCustomerInfo:");
          e.printStackTrace();
          throw e;
       }
     }

    public Product enterProductNumber(int productNumber) throws DataAccessException {
        this.currentProduct = productCtrl.findProduct(productNumber);
    	
    	return this.currentProduct;
    }

    public OrderLineItem enterQuantity(int qty) throws DataAccessException {
        if (this.currentProduct == null || this.currentOrder == null || qty <= 0) {
        	return null;
        }
        
        Price price = productCtrl.getCurrentPrice(this.currentProduct);
        double unitPrice = price != null ? price.getPrice() : 0.0;
        
        OrderLineItem item = new OrderLineItem(qty, this.currentProduct, unitPrice);
        this.currentOrder.addItem(item);
        productCtrl.reserve(this.currentProduct, qty);
        
        return item;
    }
    
    public SaleOrder confirmOrder() throws DataAccessException {
      if (this.currentOrder == null) {
    	  return null;
      }
      
      this.currentOrder.setStatus("CONFIRMED");
      int orderNo = orderDAO.save(this.currentOrder);
      
      return this.currentOrder;
    }
    
    public SaleOrder getCurrentOrder() {
    	return this.currentOrder;
    }
}