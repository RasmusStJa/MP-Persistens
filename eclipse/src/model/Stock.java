package model;

import java.util.Objects;

public class Stock {
	private Product product;
	private Warehouse warehouse;
	private int availableQty;
	
	public Stock(Product product, Warehouse warehouse, int availableQty) {
		this.product = product;
		this.warehouse = warehouse;
		this.availableQty = availableQty;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Stock))
			return false;
		Stock stock = (Stock) o;
		return availableQty == stock.availableQty &&
				Objects.equals(product, stock.product) &&
				Objects.equals(warehouse, stock.warehouse);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(product, warehouse, availableQty);
	}
	
	@Override
	public String toString() {
		return "Stock [product=" + (product != null ? product.getName() : "null") 
				+ ", warehouse=" + (warehouse != null ? warehouse.getName() : "null")
				+ ", availableQty=" + availableQty + "]";
	}
}
