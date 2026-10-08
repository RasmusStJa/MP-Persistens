package model;

import java.util.Objects;

public class GunReplica extends Product {
	private String calibre;
	private String material;
	
	public GunReplica(int productNumber, String name, int minStock, int reservedStock, String type, String calibre, String material) {
		super(productNumber, name, minStock, reservedStock, type);
		this.calibre = calibre;
		this.material = material;
	}
	
	public GunReplica(int productNumber) {
		super(productNumber);
	}

	
	
	public String getCalibre() {
		return calibre;
	}

	public void setCalibre(String calibre) {
		this.calibre = calibre;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof GunReplica))
			return false;
		if (!super.equals(o))
			return false;
		GunReplica gunReplica = (GunReplica) o;
		return Objects.equals(calibre, gunReplica.calibre) && Objects.equals(material, gunReplica.material);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), calibre, material);
	}
	
	@Override
	public String toString() {
		return "Gun replica [" + super.toString() + ", calibre=" + calibre + ", material=" + material + "]";
	}
	
}
