package model;

public enum CustomerType {
	PRIVATE,
	CLUB;
	
	@Override
	public String toString() {
		return switch (this) {
			case PRIVATE -> "Private";
			case CLUB -> "Club";
		};
	}
	
	public static CustomerType toType(String t) {
		return switch (t) {
			case "Private" -> PRIVATE; 
			case "Club" -> CLUB;
		default -> throw new IllegalArgumentException("Unexpected value: " + t); 
		};
	}

}
