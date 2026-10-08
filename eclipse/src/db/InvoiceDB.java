package db;

import model.Invoice;
import model.SaleOrder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class InvoiceDB implements InvoiceDAO {

	private static final String INSERT_INVOICE_Q =
			"INSERT INTO invoice (dueDate, paymentDate, orderNo_id) VALUES (?, ?, ?)";
	
	private static final String FIND_BY_INVOICE_NO_Q =
			"SELECT invoiceNo, dueDate, paymentDate, orderNo_id from invoice WHERE invoice = ?";;
	
	private static final String FIND_BY_ORDER_NO_Q =
			"SELECT invoiceNo, dueDate, paymentDate, orderNo_id FROM invoice WHERE orderNo_id = ?";

	@Override
	public int save(Invoice invoice) throws DataAccessException {
		int generatedInvoiceNo = -1;
		
		try (Connection con = DBConnection.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(INSERT_INVOICE_Q, Statement.RETURN_GENERATED_KEYS)) {
			
			LocalDate dueDate = (invoice.getDueDate() != null) ? invoice.getDueDate() : LocalDate.now().plusDays(14);
			LocalDate paymentDate = (invoice.getPaymentDate() != null) ? invoice.getPaymentDate() : dueDate;
			ps.setDate(1, Date.valueOf(dueDate));
			ps.setDate(2, Date.valueOf(paymentDate));
			if (invoice.getOrder() != null) {
				ps.setInt(3, invoice.getOrder().getOrderNo());
			}
			ps.executeUpdate();
			
			try (ResultSet rsKeys = ps.getGeneratedKeys()) {
				if (rsKeys.next()) {
					generatedInvoiceNo = rsKeys.getInt(1);
					invoice.setInvoiceNo(generatedInvoiceNo);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("kunne ikke gemme/færdigøre invoice: " + e.getMessage(), e);
		}
		
		return generatedInvoiceNo;
	}
	
	@Override
	public Invoice findByInvoiceNo(int invoiceNo) throws DataAccessException {
		Invoice invoice = null;
		
		try (Connection con = DBConnection.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_INVOICE_NO_Q)) {
			
			ps.setInt(1, invoiceNo);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					LocalDate dueDate = rs.getDate("dueDate").toLocalDate();
					LocalDate paymentDate = rs.getDate("paymentDate").toLocalDate();
					int orderNo = rs.getInt("orderNo_id");
					
					OrderDB orderDB = new OrderDB();
					SaleOrder order = orderDB.findByOrderNo(orderNo);
					invoice = new Invoice(invoiceNo, dueDate, paymentDate, order);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Fejl under fosøg på at hente invoice med invoiceNo: " + invoiceNo, e);
		}
		
		return invoice;
	}
	
	@Override
	public Invoice findByOrderNo(int orderNo) throws DataAccessException {
		Invoice invoice = null;
		
		try (Connection con = DBConnection.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(FIND_BY_ORDER_NO_Q)) {
			
			ps.setInt(1, orderNo);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					int invoiceNo = rs.getInt("invoiceNo");
					LocalDate dueDate = rs.getDate("dueDate").toLocalDate();
					LocalDate paymentDate = rs.getDate("paymentDate").toLocalDate();
					
					OrderDB orderDB = new OrderDB();
					SaleOrder order = orderDB.findByOrderNo(orderNo);
					invoice = new Invoice(invoiceNo, dueDate, paymentDate, order);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Fejl under fosøg på at hente invoice med orderNo: " + orderNo, e);
		}
		
		return invoice;
	}
}
