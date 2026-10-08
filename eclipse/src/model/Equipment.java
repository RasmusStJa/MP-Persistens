package model;

import java.util.Objects;

public class Equipment extends Product {
	private String material;
	private String style;
	
	public Equipment(int productNumber, String name, int minStock, int reservedStock, String type, String material, String style) {
		super(productNumber, name, minStock, reservedStock, type);
		this.material = material;
		this.style = style;
	}
	
	public Equipment(int productNumber) {
		super(productNumber);
	}
	
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Equipment))
			return false;
		if (!super.equals(o))
			return false;
		Equipment equipment = (Equipment) o;
		return Objects.equals(material, equipment.material) && Objects.equals(style, equipment.style);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), material, style);
	}
	
	@Override
	public String toString() {
		return "Equipment [" + super.toString() + ",material=" + material + ", style=" + style + "]";
	}
	
}
