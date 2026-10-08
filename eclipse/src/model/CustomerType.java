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
}
