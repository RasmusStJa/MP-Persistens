package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 * 
 * lad den forblive udkommenteret indtil scripts er blevet implementeret i eclipse
 * 
 */

public class TestUtilitiesDB {

	public static void main(String[] args) throws SQLException, IOException, DataAccessException {
		cleanDB(); // call to the utility class that resets the database
		System.out.println("cleaned");
		createTablesDB();
		System.out.println("restored tables");	
		insertDataDB();
		System.out.println("restored data");
	}

	public static void cleanDB() throws SQLException, IOException, DataAccessException {

		try (Statement stmt = DBConnection.getInstance().getConnection().createStatement()) {
			String sqlClean = readAllBytesJava("scripts/dropTables.sql");
			stmt.executeUpdate(sqlClean);
		}
	}

	public static void createTablesDB() throws SQLException, IOException, DataAccessException {
		try (Statement stmt = DBConnection.getInstance().getConnection().createStatement()) {

			String sqlRestore = readAllBytesJava("scripts/createTables.sql");
			stmt.executeUpdate(sqlRestore);

		}
	}

	public static void insertDataDB() throws SQLException, IOException, DataAccessException {
		try (Statement stmt = DBConnection.getInstance().getConnection().createStatement()) {

			String sqlInsert = readAllBytesJava("scripts/insertData.sql");
			stmt.executeUpdate(sqlInsert);
		}

	}

	// Read file content into string with - Files.readAllBytes(Path path)

	private static String readAllBytesJava(String filePath) throws IOException {
		String content = "";
		content = new String(Files.readAllBytes(Paths.get(filePath)));
		return content;
	}



}