package db;

import model.Customer;
import model.OrderLineItem;
import model.Product;
import model.SaleOrder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDB implements OrderDAO {
	
	private static final String INSERT_ORDER_Q =
			"INSERT INTO saleOrder (cust_phoneno_id, date, deliveryStatus, deliveryDate, discountGiven) " +
			"VALUES (?, ?, ?, ?, ?)";
	
	private static final String INSERT_LINE_ITEM_Q =
			"INSERT INTO orderLineItem (quantity, orderNo_id, productNumber_id) " +
			"VALUES (?, ?, ?)";
	
	private static final String FIND_BY_ORDER_NO_Q =
			"SELECT orderNo, cust_phoneno_id, date, deliveryStatus, deliveryDate, discountGiven " +
			"FROM saleOrder WHERE orderNo = ?";
	
	private static final String FIND_LINE_ITEMS_Q =
			"SELECT quantity, productNumber_id FROM orderLineItem WHERE orderNo_id = ?";
	
	private static final String UPDATE_STATUS_Q =
			"UPDATE saleOrder SET deliveryStatus = ? WHERE orderNo = ?";

	@Override
	public int save(SaleOrder o) throws DataAccessException {
		int generatedOrderNo = -1;
		Connection con = null;
		
		try {
			con = DBConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			
			try (PreparedStatement psOrder = con.prepareStatement(INSERT_ORDER_Q, Statement.RETURN_GENERATED_KEYS)) {
				psOrder.setString(1, o.getCustomer() != null ? o.getCustomer().getPhoneno() : null);
				psOrder.setDate(2, o.getDate() != null ? Date.valueOf(o.getDate()) : Date.valueOf(LocalDate.now()));
				psOrder.setString(3, o.getStatus());
				psOrder.setDate(4, o.getDeliveryDate() != null ? Date.valueOf(o.getDeliveryDate()) : null);
				psOrder.setDouble(5, o.getDiscount());
				
				psOrder.executeUpdate();
				
				try (ResultSet rsKeys = psOrder.getGeneratedKeys()) {
					if (rsKeys.next()) {
						generatedOrderNo = rsKeys.getInt(1);
						o.setOrderNo(generatedOrderNo);
					}
				}
			}
			if (generatedOrderNo != -1 && o.getLines() != null) {

				try (PreparedStatement psItem = con.prepareStatement(INSERT_LINE_ITEM_Q)) {
					for (OrderLineItem item : o.getLines()) {
						psItem.setInt(1, item.getQuantity());
						psItem.setInt(2, generatedOrderNo);
						psItem.setInt(3, item.getProduct().getProductNumber());
						
						psItem.addBatch();
					}
					psItem.executeBatch();
				}
			}
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			throw new DataAccessException("kunne ikke gemme/færdigøre ordre: " + e.getMessage(), e);
		} finally {
			if (con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return generatedOrderNo;
	}
	
	@Override
	public SaleOrder findByOrderNo(int orderNo) throws DataAccessException {
		SaleOrder order = null;
		
		try (Connection con = DBConnection.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(FIND_BY_ORDER_NO_Q)) {
			
			ps.setInt(1, orderNo);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String phoneno = rs.getString("cust_phoneno_id");
					LocalDate date = rs.getDate("date") != null ? rs.getDate("date").toLocalDate() : null;
					String status = rs.getString("deliveryStatus");
					LocalDate deliveryDate = rs.getDate("deliveryDate") != null ? rs.getDate("deliveryDate").toLocalDate() : null;
					double discount = rs.getDouble("discountGiven");
					
					Customer customer = new Customer(phoneno);
					order = new SaleOrder(orderNo, date, status, deliveryDate, discount, customer);
					
					order.setLines(findLineItemsForOrder(con, orderNo));
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Fejl under forsøg på at hente ordre ud fra ordreNo: " + orderNo, e);
		}
		
		return order;
	}
	
	@Override
	public boolean updateDeliveryStatus(int orderNo, String status) throws DataAccessException {
		int rowsUpdated = 0;
		
		try (Connection con = DBConnection.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_STATUS_Q)) {
			
			ps.setString(1, status);
			ps.setInt(2, orderNo);
			
			rowsUpdated = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Fejl under opdatering af status", e);
		}
		
		return rowsUpdated > 0;
	}
	
	private List<OrderLineItem> findLineItemsForOrder(Connection con, int orderNo) throws DataAccessException, SQLException {
		List<OrderLineItem> items = new ArrayList<>();
		ProductDB productDB = new ProductDB();
		
		try (PreparedStatement ps = con.prepareStatement(FIND_LINE_ITEMS_Q)) {
			ps.setInt(1, orderNo);
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int qty = rs.getInt("quantity");
					int prodNum = rs.getInt("productNumber_id");
					
					Product product = productDB.findByProductNumber(prodNum);
					items.add(new OrderLineItem(qty, product));
				}
			}
		}
		
		return items;
	}
}








