package org.tutorials;

/***
 * 
 * @author ThinkPad
 * jdbc:oracle:thin:@//localhost:1521/xe
 * hr/sea4me
 */

import java.sql.*;
import javax.sql.*;
import oracle.jdbc.driver.*;
import oracle.jdbc.pool.OracleDataSource;
import utils.DbUtils;

public class DataSource {
	
	public static void main(String args[]) throws SQLException {
		
		// Create a OracleDataSource instance explicitly
		// OracleDataSource ods = new OracleDataSource();
		oracle.jdbc.pool.OracleDataSource ods = new oracle.jdbc.pool.OracleDataSource();

		// Set the user name, password, driver type and network protocol
//    ods.setServerName("localhost");
//    ods.setPortNumber(1521);
//    ods.setDriverType("thin");
//    ods.setServiceName("xe");

		ods.setUser("hr");
		ods.setPassword("sea4me");

		ods.setURL("jdbc:oracle:thin:@//localhost:1521/xe");
		// Retrieve a connection
		Connection conn = ods.getConnection();
		getUserName(conn);
		// Close the connection
		conn.close();
		conn = null;
	}

	static void getUserName(Connection conn) throws SQLException {
		// Create a Statement
		Statement stmt = conn.createStatement();

		// Select the ENAME column from the EMP table
		ResultSet rset = stmt.executeQuery("select USER from dual");

		String sqlString = DbUtils.generateActualSql("select USER from dual", "dude");
		System.out.println(" sqlString = " + sqlString);

		// Iterate through the result and print the employee names
		while (rset.next())
			System.out.println("User name is " + rset.getString(1));

		// Close the RseultSet
		rset.close();
		rset = null;

		// Close the Statement
		stmt.close();
		stmt = null;
	}
}