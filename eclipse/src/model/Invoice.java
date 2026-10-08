package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Invoice {
	private int invoiceNo;
	private LocalDate dueDate;
	private LocalDate paymentDate;
	private int overDueCount;
	private SaleOrder order;
	
	public int getInvoiceNo() 			{ return invoiceNo; 	}
	public LocalDate getDueDate() 		{ return dueDate; 		}
	public LocalDate getPaymentDate() 	{ return paymentDate; 	}
	public int getOverDueCount()		{ return overDueCount;	}
	
	public void setInvoiceNo(final int invoiceNo) 		{ this.invoiceNo = invoiceNo; 		}
	public void setDueDate(final LocalDate dueDate) 	{ this.dueDate = dueDate; 			}
	public void setPaymentDate(LocalDate paymentDate) 	{
		this.paymentDate = paymentDate;
		overDueCount = (int)ChronoUnit.DAYS.between(dueDate, paymentDate);
	}

	public Invoice(int invoiceNo, LocalDate dueDate, SaleOrder order) {
		setInvoiceNo(invoiceNo);
		setDueDate(dueDate);
		this.order = order;
	}
	
	public double getAmountToPay() {
		if (order != null) {
			return order.getTotal();
		}
		return 0.0;
	}
	
	@Override
	public String toString() {
		return "Invoice [invoiceNo=" + invoiceNo + ", dueDate=" + dueDate + ", paymentDate=" + paymentDate
				+ ", overDueCount=" + overDueCount + "]";
	}
}
