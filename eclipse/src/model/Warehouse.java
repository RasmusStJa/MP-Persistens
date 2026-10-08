package model;

import java.util.Objects;

public class Warehouse {
	private int number;
	private String name;
	private String description;
	
	public Warehouse(int number, String name, String description) {
		this(number);
		this.name = name;
		this.description = description;
	}
	
	public Warehouse(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Warehouse(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Warehouse))
			return false;
		Warehouse warehouse = (Warehouse) o;
		return number == warehouse.number && Objects.equals(name, warehouse.name) && Objects.equals(description, warehouse.description);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(number, name, description);
	}
	
	@Override
	public String toString() {
		return "Warehouse [number=" + number + ", name=" + name + ", description=" + description +"]";
	}
	
}
