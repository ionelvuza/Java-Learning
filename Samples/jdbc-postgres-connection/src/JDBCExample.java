import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/** 
JDBC example
*/
class JDBCExample {

	public static void main(String[] argv) {

		// Debug message
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			// Register postgre SQL Driver
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		// Postgre SQL Driver registered
		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			// Establish Postgre connection
			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/postgres", "postgres",
					"postgres");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		// Print Table contents
		DatabaseMetaData md;
		try {
			md = connection.getMetaData();
			String[] types = {"TABLE"};
			ResultSet rs = md.getTables(null, null, "%", types);
			while (rs.next()) {
			  System.out.println(rs.getString(2));
			  System.out.println(rs.getString(3));
			  System.out.println(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}