package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleOrder {
    private int orderNo;
    private LocalDate date;
    private String status;
    private double discount;
    private Customer customer;
    private List<OrderLineItem> lines;
    
    public SaleOrder() {
        date = LocalDate.now(); 
        status = "OPEN";       
        discount = 0.0;   
        lines = new ArrayList<OrderLineItem>();
    }
    
    public OrderLineItem addOrderLine(Product p, int qty, int price) {
        OrderLineItem newItem = new OrderLineItem(p, qty, price);
        this.lines.add(newItem);
        return newItem;
    }
    
	public void setDate(final LocalDate date) 				{ this.date = date; 					}
	public void setDiscountGiven(double discount) 			{ this.discount = -Math.abs(discount); 	}
	public void addItem(final OrderLineItem OLI) 			{ lines.add(OLI); 						}
	public void setLines(final List<OrderLineItem> lines) 	{ this.lines = lines; 					}
	public void setCustomer(final Customer c) 				{ customer = c; 						}
    public void setStatus(final String s) 					{ status = s; 							}
    public void setOrderNo(final int n) 					{ orderNo = n; 							}
    
    public int getOrderNo() 				{ return orderNo; 	}
    public List<OrderLineItem> getLines() 	{ return lines; 	}
    public final LocalDate getDate() 		{ return date; 		}
	public final String getStatus() 		{ return status; 	}
	public final double getDiscountGiven() 	{ return discount; 	}
	public final Customer getCustomer() 	{ return customer; 	}
    
	public double getSum() {
		double sum = 0;
        for (final OrderLineItem line : lines) { sum += line.getSubtotal(); }
        return sum;
	}
	
	public double getDiscountOnTotal() {
		return getTotal() - discount;
	}
	
    public double getTotal() {
        double sum = getSum();
        
        sum -= discount;
        if (sum < 0) { return 0; }
        return sum;
    }
}