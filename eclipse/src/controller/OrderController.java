package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import db.DataAccessException;
import db.OrderDAO;
import model.Customer;
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
        Customer c = customerCtrl.findCustomer(phone);
        if (c != null && this.currentOrder != null) {
        	this.currentOrder.setCustomer(c);

        	if (c.getType() != null && c.getType().name().equalsIgnoreCase("CLUB")) {
            	this.currentOrder.setDiscount(10.0);
        	}
        }
        return c;
    }

    public Product enterProductNumber(int productNumber) throws DataAccessException {
        this.currentProduct = productCtrl.findProduct(productNumber);
    	
    	return this.currentProduct;
    }

    public OrderLineItem enterQuantity(int qty) throws DataAccessException {
        if (this.currentProduct == null || this.currentOrder == null) {
            return null;
        }
        if (qty < 0) {
            throw new IllegalArgumentException("Quantity (" + qty + ") must be greater than 0");
        }
        if (qty > this.currentProduct.getReservedStock()) {
            throw new IllegalArgumentException("Quantity exceeds available stock");
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

	@Override
	public String toString() {
		return "OrderController [currentOrder=" + currentOrder + ", customerCtrl=" + customerCtrl + ", productCtrl="
				+ productCtrl + ", orderDAO=" + orderDAO + ", currentProduct=" + currentProduct + "]";
	}
}