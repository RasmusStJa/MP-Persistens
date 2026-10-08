package model;

import java.util.Objects;
import java.time.LocalDateTime;

public class Price {
	private Product product;
	private LocalDateTime timestamp;
	private double price;
	
	public Price(Product product, LocalDateTime timestamp, double price) {
		this.product = product;
		this.timestamp = timestamp;
		this.price = price;
	}
	
	public Price(LocalDateTime timestamp, double price) {
		this.timestamp = timestamp;
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Price))
			return false;
		Price price1 = (Price) o;
		return Double.compare(price1.price, price) == 0 &&
				Objects.equals(product, price1.product) &&
				Objects.equals(timestamp, price1.timestamp);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(product, timestamp, price);
	}
	
	@Override
	public String toString() {
		return "Price [timestamp=" + timestamp + ", price=" + price + "]";
	}
}
