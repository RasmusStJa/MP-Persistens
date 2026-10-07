package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleOrder {
    
    private int orderNo;
    private LocalDate date;
    private String status;
    private double discountGiven; 
    private Customer customer;
    private List<OrderLineItem> lines;

   
    public SaleOrder() {
        this.date = LocalDate.now(); 
        this.status = "OPEN";       
        this.discountGiven = 0.0;   
        this.lines = new ArrayList<>();
    }

    
    public void setCustomer(Customer c) {
        this.customer = c;
    }


    public OrderLineItem addOrderLine(Product p, int qty) {
        OrderLineItem newItem = new OrderLineItem(p, qty);
        this.lines.add(newItem);
        return newItem;
    }

    public double getTotal() {
        double sum = 0;
        for (OrderLineItem line : lines) {
            sum += line.getSubtotal();
        }
        return sum + this.discountGiven;
    }

    public void applyDiscount() {
        double currentSubtotal = 0;
        for (OrderLineItem line : lines) {
            currentSubtotal += line.getSubtotal();
      
        }
        
        if (currentSubtotal > 1500) { 
            this.discountGiven = currentSubtotal * 0.10;
        } else {
            this.discountGiven = 0.0;
        }
    }

    public void setStatus(String s) {
        this.status = s;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int n) {
        this.orderNo = n;
    }

    public List<OrderLineItem> getLines() {
        return lines;
    }
}