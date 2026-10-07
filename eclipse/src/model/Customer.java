package model;

public class Customer {
	int customerId;
	private String name;
	private String address;
	private String phoneNo;
	private String email;
	private String type;
	
	public final int getCustomerId() 	{ return customerId; 	}
	public final String getName() 		{ return name; 			}
	public final String getAddress() 	{ return address; 		}
	public final String getPhoneNo() 	{ return phoneNo; 		}
	public final String getEmail() 		{ return email; 		}
	public final String getType() 		{ return type; 			}
	
	public final void setCustomerId(final int customerId) { this.customerId = customerId; }
	
	public Customer(final int customerId, final String name, final String address, final String phoneNo, final String email, final String type) {
		this.customerId = customerId;
		this.name 		= name;
		this.address 	= address;
		this.phoneNo 	= phoneNo;
		this.email 		= email;
		this.type 		= type;
	}
}
