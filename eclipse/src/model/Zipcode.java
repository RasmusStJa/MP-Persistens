package model;

import java.util.Objects;

public class Zipcode {
	
	private int zipcode;
	private String city;
	
	public Zipcode(int zipcode, String city) {
		this.zipcode = zipcode;
		this.city = city;
	}
	/*
	 *Removed bc there's no way to get a city only from a zipcode
	public Zipcode(int zipcode) {
		setZipcode(zipcode);
	}
	*/
	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = Math.abs(zipcode);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Zipcode))
			return false;
		Zipcode zipcode1 = (Zipcode) o;
		return zipcode == zipcode1.zipcode && Objects.equals(city, zipcode1.city);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(zipcode, city);
	}
	
	@Override
	public String toString() {
		return zipcode + " " + city;
	}
	
}
