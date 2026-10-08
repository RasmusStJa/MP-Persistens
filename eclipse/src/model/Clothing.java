package model;

import java.util.Objects;

public class Clothing extends Product {
	private String size;
	private String colour;
	
	public Clothing(int productNumber, String name, int minStock, int reservedStock, String type, String size, String colour) {
		super(productNumber, name, minStock, reservedStock, type);
		this.size = size;
		this.colour = colour;
	}
	
	public Clothing(int productNumber) {
		super(productNumber);
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Clothing))
			return false;
		if (!super.equals(o))
			return false;
		Clothing clothing = (Clothing) o;
		return Objects.equals(size, clothing.size) && Objects.equals(colour, clothing.colour);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), size, colour);
	}
	
	@Override
	public String toString() {
		return "Clothing [" + super.toString() + ", size=" + size + ", colour=" + colour + "]";
	}

}
