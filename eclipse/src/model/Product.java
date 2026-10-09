package model;

import java.util.Objects;

public class Product { 
	private int productNumber;
	private String name;
	private int minStock;
	private int reservedStock;
	private String type;
    
    public Product(int productNumber, String name, int minStock, int reservedStock, String type) {
    	this(productNumber);
    	this.name = name;
    	this.minStock = minStock;
    	this.reservedStock = reservedStock;
    	this.type = type;
    }

    public Product(int productNumber) {
    	this.productNumber = productNumber;
    }
    
    public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getReservedStock() {
		return reservedStock;
	}

	public void setReservedStock(int q) {
        this.reservedStock = q;
    }
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Product))
			return false;
		Product product = (Product) o;
		return productNumber == product.productNumber &&
				minStock == product.minStock &&
				reservedStock == product.reservedStock &&
				Objects.equals(name, product.name) &&
				Objects.equals(type, product.type);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(productNumber, name, minStock, reservedStock, type);
	}
	
	@Override
	public String toString() {
		return "Product [productNumber=" + productNumber + ", name=" + name + ", minStock=" + minStock + ", reservedStock=" + reservedStock + ", type=" + type + "]";
	}
	
}
