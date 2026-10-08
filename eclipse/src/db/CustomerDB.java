package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;

public class CustomerDB implements CustomerDAO {
	
	private static final String SELECT_ALL_PNUM =
			"select phoneno, name, address, zipcode from Zipcode, type, where phoneno = ?";
	
	private PreparedStatement selectByPnum;

	
	
	public CustomerDB() throws DataAccessException {
		try {
			selectByPnum = DBConnection.getInstance().getConnection().prepareStatement(SELECT_ALL_PNUM);
	} catch (SQLException e) {
		throw new DataAccessException("Could not prepare statements",e);
	}
		
	}

	@Override
	public Customer findByPhone(String phone) throws DataAccessException  {
		try {
			selectByPnum.setString(1, phone);
			ResultSet rs = selectByPnum.executeQuery();
			Customer e = buildObject(rs);
			return e;
		} catch (SQLException e) {
			throw new DataAccessException("Could not bind param or select customer by phonenumber", e);
		}

	}
	
	private Customer buildObject(ResultSet rs) throws DataAcessException {
		Customer e = null;
		try {
			if(rs.next()) {
				e = new Customer(
						rs.getString("phoneno"),
						rs.getString("name"),
						rs.getString("address"),
						new Zipcode(rs.getInt("zipcode"), ""),
						CustomerType.toType(rs.getString("type")));
						
			}
		} catch (SQLException e1) {
			throw new DataAccessException("Could not read result set for employee", e1);
		}
		return e;
	}

	@Override
	public void save(Customer c) {
		// TODO Auto-generated method stub
		
	}

}
