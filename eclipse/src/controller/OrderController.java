package controller;


public class OrderController {
    

	
	private SaleOrder order;
    private CustomerController customerCtrl;
    private ProductController productCtrl;
    private OrderDAO orderDAO;

   
    public OrderController() {
        this.customerCtrl = new CustomerController();
        this.productCtrl = new ProductController();
        this.orderDAO = new OrderDAO();
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
        this.order.setOrderNo(555); 
        orderDAO.save(this.order);
        return this.order;
    }
