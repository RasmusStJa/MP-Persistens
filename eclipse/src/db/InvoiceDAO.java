package db;

import model.Invoice;

public interface InvoiceDAO {
	int save(Invoice invoice) throws DataAccessException;
	Invoice findByInvoiceNo(int invoiceNo) throws DataAccessException;
	Invoice findByOrderNo(int orderNo) throws DataAccessException;
}
