package model;

public class costumer {
	int costumerId;
	String name;
	String address;
	String phoneNo;
	String email;
	String type;
	
	public final int getCostumerId() 	{ return costumerId; 	}
	public final String getName() 		{ return name; 			}
	public final String getAddress() 	{ return address; 		}
	public final String getPhoneNo() 	{ return phoneNo; 		}
	public final String getEmail() 		{ return email; 		}
	public final String getType() 		{ return type; 			}
	
	public final void setCostumerId(final int costumerId) { this.costumerId = costumerId; }
	
	public costumer(final int costumerId, final String name, final String address, final String phoneNo, final String email, final String type) {
		this.costumerId = costumerId;
		this.name 		= name;
		this.address 	= address;
		this.phoneNo 	= phoneNo;
		this.email 		= email;
		this.type 		= type;
	}
}
