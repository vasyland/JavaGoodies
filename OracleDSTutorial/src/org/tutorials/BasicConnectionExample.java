package org.tutorials;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import oracle.ucp.jdbc.PoolDataSource;

public class BasicConnectionExample {
   public static void main(String args[]) throws SQLException {
      try 
      {
         //Create pool-enabled data source instance.
         
         PoolDataSource  pds = PoolDataSourceFactory.getPoolDataSource();
         
         //set the connection properties on the data source.
         
         pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
         pds.setURL("jdbc:oracle:thin:@//localhost:1521/xe");
         pds.setUser("hr");
         pds.setPassword("sea4me");     
         
         //Override any pool properties.
         
         pds.setInitialPoolSize(5);
         
         //Get a database connection from the datasource.
         
         Connection conn = pds.getConnection();
         
         System.out.println("\nConnection obtained from " +
          "UniversalConnectionPool\n");
         
         //do some work with the connection.
         Statement stmt = conn.createStatement();
        // stmt.execute("select USER from dual");
         
         ResultSet rset = stmt.executeQuery ("select USER from dual");
         // Iterate through the result and print the employee names
         while (rset.next ())
           System.out.println ("User name is " + rset.getString (1));
         //Close the Connection.
         
         conn.close();
         conn=null;
         
         System.out.println("Connection returned to the " +
          "UniversalConnectionPool\n");
         
      }
      catch(SQLException e)
      {
         System.out.println("BasicConnectionExample - " +
          "main()-SQLException occurred : "
              + e.getMessage());
      }
   }
}