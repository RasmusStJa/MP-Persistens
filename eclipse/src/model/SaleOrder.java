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
    private Invoice invoice;
    
    public SaleOrder(final LocalDate date, final String status, final double discout, final ArrayList<OrderLineItem> lines) {
        setDate(date);
        setStatus(status);
        setDiscount(discount);
        setLines(lines);
    }
    
	public void setDate(final LocalDate date) 				{ this.date = date; 					}
	public void setDiscount(double discount) 				{ this.discount = -Math.abs(discount); 	}
	public void addItem(final OrderLineItem OLI) 			{ lines.add(OLI); 						}
	public void setLines(final List<OrderLineItem> lines) 	{ this.lines = lines; 					}
	public void setCustomer(final Customer c) 				{ customer = c; 						}
    public void setStatus(final String s) 					{ status = s; 							}
    public void setOrderNo(final int n) 					{ orderNo = n; 							}
    public void setInvoice(final Invoice invoice)			{ this.invoice = invoice;				}
    
    public int getOrderNo() 				{ return orderNo; 	}
    public List<OrderLineItem> getLines() 	{ return lines; 	}
    public LocalDate getDate() 				{ return date; 		}
	public String getStatus() 				{ return status; 	}
	public double getDiscount() 			{ return discount; 	}
	public Customer getCustomer() 			{ return customer; 	}
    public Invoice getInvoice()				{ return invoice;	}
	
	public double getSum() {
		double sum = 0;
        for (final OrderLineItem line : lines) { sum += line.getSubtotal(); }
        return sum;
	}
	/*
	public double getDiscountOnTotal() {
		return getTotal() - discount;
	}
	*/
	
    public double getTotal() {
        double sum = getSum();
        
        sum -= getDiscount();
        if (sum < 0) { return 0; }
        return sum;
    }
}