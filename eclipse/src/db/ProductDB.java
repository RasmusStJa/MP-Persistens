package db;

import model.Clothing;
import model.Equipment;
import model.GunReplica;
import model.Price;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProductDB implements ProductDAO{
	
	private static final String FIND_BY_NUMBER_Q =
			"SELECT p.productNumber, p.name, p.minStock, p.reservedStock, p.type, " + 
			"c.size, c.colour, " +
			"e.material AS equip_material, e.style, " +
			"g.calibre, g.material AS gun_material " +
			"FROM product p " +
			"LEFT JOIN clothing c ON p.productNumber = c.productNumber " +
			"LEFT JOIN equipment e ON p.productNumber = e.productNumber " +
			"LEFT JOIN gunReplica g ON p.productNumber = g.productNumber " +
			"WHERE p.productNumber = ?";
	
	private static final String FIND_CURRENT_PRICE_Q = 
			"SELECT TOP 1 productNumber_id, timestamp, price " + 
			"FROM price " +
			"WHERE productNumber_id = ? " +
			"ORDER BY timestamp DESC";
	
	private static final String UPDATE_RESERVED_STOCK_Q =
			"UPDATE product SET reservedStock = ? WHERE productNumber = ?";
	
	@Override
	public Product findByProductNumber(int productNumber) throws DataAccessException {
		Product product = null;
		
		try (Connection con = DBConnection.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_NUMBER_Q)) {
			
			ps.setInt(1, productNumber);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					product = buildProductFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Kunne ikke finde produkt ud fra givne productNumber: " + productNumber + ".", e);
		}
		
		return product;
	}
	
	@Override
	public Price findCurrentPriceByProductNumber(int productNumber) throws DataAccessException {
		Price price = null;
		
		try (Connection con = DBConnection.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(FIND_CURRENT_PRICE_Q)) {
			ps.setInt(1, productNumber);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					LocalDateTime timestamp = rs.getTimestamp("timestamp").toLocalDateTime();
					double priceValue = rs.getDouble("price");
					price = new Price(timestamp, priceValue);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Kunne ikke finde nuværende pris på produkt", e);
		}
		
		return price;
	}
	
	@Override
	public boolean updateReservedStock(Product p) throws DataAccessException{
		int  rowsUpdated = 0;
		
		try (Connection con = DBConnection.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_RESERVED_STOCK_Q)) {
			ps.setInt(1, p.getReservedStock());
			ps.setInt(2, p.getProductNumber());
			
			rowsUpdated = ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException("Kunne ikke opdatere reserveret lager", e);
		}
		
		return rowsUpdated > 0;
	}
	
	private Product buildProductFromResultSet(ResultSet rs) throws SQLException {
		int productNumber = rs.getInt("productNumber");
		String name = rs.getString("name");
		int minStock = rs.getInt("minStock");
		int reservedStock = rs.getInt("reservedStock");
		String type = rs.getString("type");
		
		if (type != null) {
			switch (type.trim().toLowerCase()) {
			case "clothing":
				String size = rs.getString("size");
				String colour = rs.getString("colour");
				return new Clothing(productNumber, name, minStock, reservedStock, type, size, colour);
				
			case "equipment":
				String equipMaterial = rs.getString("equip_material");
				String style = rs.getString("style");
				return new Equipment(productNumber, name, minStock, reservedStock, type, equipMaterial, style);
				
			case "gunreplica":
				String calibre = rs.getString("calibre");
				String gunMaterial = rs.getString("gun_material");
				return new GunReplica(productNumber, name, minStock, reservedStock, type, calibre, gunMaterial);
				
			default:
				return new Product(productNumber, name, minStock, reservedStock, type);
			}
		}
		
		return new Product(productNumber, name, minStock, reservedStock, type);
	}
}
