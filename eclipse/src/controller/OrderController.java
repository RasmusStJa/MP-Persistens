package controller;


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
        this.order = new SaleOrder();
        return this.order;
    }

    public Customer enterCustomerInfo(String phone, String email) {
        Customer c = customerCtrl.findCustomer(phone, email);
        this.order.setCustomer(c);
        return c;
    }

    public Product enterProductNumber(int productNumber) {
        return productCtrl.findProduct(productNumber);
    }

    public OrderLineItem enterQuantity(int qty) {
        Product p = productCtrl.findProduct(123); 
        OrderLineItem item = this.order.addOrderLine(p, qty);
        productCtrl.reserve(p, qty);
        this.order.applyDiscount();
        this.order.getTotal();
        return item;
    }
    
    public SaleOrder confirmOrder() {
        this.order.setStatus("CONFIRMED");
        this.order.setOrderNo(67);
        orderDAO.save(this.order);
        return this.order;
    }
}