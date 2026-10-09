package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.*;

public class CustomerDB implements CustomerDAO {
	
	private static final String SELECT_ALL_PNUM =
			"select phoneno, name, address, zipcode from Zipcode, type, WHERE phoneno = ?";
	
	private static final String SELECT_BY_PHONE_Q = 
			"SELECT c.phoneno, c.name, c.address, c.zipcode, z.city, c.type " + "FROM customer c " +
			"LEFT JOIN zipcode z ON c.zipcode = z.zipcode " + "WHERE RTRIM(LTRIM(c.phoneno)) = ?";
	
	private PreparedStatement selectByPnum;
	
	public CustomerDB() throws DataAccessException {
//		try {
//			selectByPnum = DBConnection.getInstance().getConnection().prepareStatement(SELECT_ALL_PNUM);
//		} catch (SQLException e) {
//			throw new DataAccessException("Could not prepare statements",e);
//		}
	}

	@Override
	public Customer findByPhone(String phone) throws DataAccessException {
	  Customer customer = null;
	  try (Connection con = DBConnection.getInstance().getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_BY_PHONE_Q)) {
	    ps.setString(1, phone != null ? phone.trim() : "");
	    try (ResultSet rs = ps.executeQuery()) {
	      if (rs.next()) {
	        customer = buildObject(rs);
	      }
	    }
	  } catch (SQLException e) {
	    throw new DataAccessException("Fejl ved opslag på telefonnummer: " + phone, e);
	  }
	  return customer;
	}
	
	private Customer buildObject(ResultSet rs) throws SQLException {
		  String phoneno = rs.getString("phoneno");
		  String name = rs.getString("name");
		  String address = rs.getString("address");
		  int zipCodeVal = rs.getInt("zipcode");
		  String city = rs.getString("city");
		  if (city == null) {
		    city = "";
		  }
		  String typeStr = rs.getString("type");
		  Zipcode zipcode = new Zipcode(zipCodeVal, city);
		  CustomerType customerType = CustomerType.toType(typeStr != null ? typeStr.trim() : "");
		  return new Customer(phoneno, name, address, zipcode, customerType);
		}
	
//	private Customer buildObject(ResultSet rs) throws SQLException {
//		  String phoneno = rs.getString("phoneno");
//		  String name = rs.getString("name");
//		  String address = rs.getString("address");
//		  int zipCodeVal = rs.getInt("zipcode");
//		  String city = rs.getString("city");
//		  if (city == null) {
//		    city = "";
//		  }
//		  String typeStr = rs.getString("type");
//		  Zipcode zipcode = new Zipcode(zipCodeVal, city); 
//		  CustomerType customerType = null;
//		  if (typeStr != null) {
//		    customerType = CustomerType.toType(typeStr.trim().toUpperCase());
//		  }
//		  return new Customer(phoneno, name, address, zipcode, customerType);
//		}
}
