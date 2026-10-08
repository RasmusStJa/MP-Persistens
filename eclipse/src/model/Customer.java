package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
	private String phoneno;
	private String name;
	private String address;
	private Zipcode zipcode;
	private CustomerType type;
	private List<SaleOrder> orders;
	
//	public final String getPhoneno() 	{ return phoneno; 		}
//	public final String getName() 		{ return name; 			}
//	public final String getAddress() 	{ return address; 		}
//	public final String getType() 		{ return type; 			}
	
	public Customer(String phoneno, String name, String address, Zipcode zipcode, CustomerType type) {
		this(phoneno);
		this.name 		= name;
		this.address 	= address;
		this.zipcode 	= zipcode;
		this.type 		= type;
	}

	public Customer(String phoneno) {
		this.phoneno = phoneno;
	}
	
	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Zipcode getZipcode() {
		return zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

//	public List<SaleOrder> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<SaleOrder> orders) {
//		this.orders = orders;
//	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		if(!(o instanceof Customer))
			return false;
		Customer customer = (Customer) o;
		return Objects.equals(phoneno, customer.phoneno) &&
				Objects.equals(name, customer.name) &&
				Objects.equals(address, customer.address) &&
				Objects.equals(zipcode, customer.zipcode) &&
				type == customer.type;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(phoneno, name, address, zipcode, type);
	}
	
	@Override
	public String toString() {
		return "Customer [phoneNo=" + phoneno + ", name=" + name + ", address=" + address + ", zipcode=" + zipcode + ", type=" + type + "]";
	}
}
