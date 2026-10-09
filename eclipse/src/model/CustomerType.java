package model;

public enum CustomerType {
	  PRIVATE,
	  CLUB;
	
	public static CustomerType toType(String type) {
	    if (type == null || type.trim().isEmpty()) {
	    	return PRIVATE;
	    }
	    
	      switch (type.trim().toUpperCase()) {
	      case "PRIVATE":
	        return PRIVATE;
	      case "CLUB":
	        return CLUB;
	      default:
	        throw new IllegalArgumentException("Unexpected value: " + type);
	      }
	   }
	}
	