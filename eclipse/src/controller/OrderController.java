package controller;


import db.DataAccessException;
import db.OrderDAO;
import model.Customer;
import model.OrderLineItem;
import model.Product;
import model.SaleOrder;

public class OrderController {
    private SaleOrder order;
    private CustomerController customerCtrl;
    private ProductController productCtrl;
    private OrderDAO orderDAO;
    
    
    public OrderController(CustomerController cc, ProductController pc, OrderDAO dao) {
        this.customerCtrl = cc;
        this.productCtrl = pc;
        this.orderDAO = dao;
    }

    public SaleOrder createOrder() {
        order = new SaleOrder();
        return order;
    }

    public Customer enterCustomerInfo(String phone) throws DataAccessException {
        Customer c = customerCtrl.findCustomer(phone);
        order.setCustomer(c);
        return c;
    }

    public Product enterProductNumber(int productNumber) {
        return productCtrl.findProduct(productNumber);
    }

    public OrderLineItem enterQuantity(int qty) {
        Product p = productCtrl.findProduct(123); 
        OrderLineItem item = order.addItem(p, qty);
        productCtrl.reserve(p, qty);
        order.getTotal();
        return item;
    }
    
    public SaleOrder confirmOrder() {
        order.setStatus("CONFIRMED");
        order.setOrderNo(67);
        orderDAO.save(order);
        return order;
    }
}