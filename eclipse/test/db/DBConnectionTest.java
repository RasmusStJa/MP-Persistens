package db;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.Connection;
import org.junit.jupiter.api.Test;

class DBConnectionTest {


	@Test
	public void testGetConnection() {
			Connection c;
			try {
				c = DBConnection.getInstance().getConnection();
				assertNotNull(c);
			} catch (DataAccessException e) {
				fail();
			}
			
	}
}
