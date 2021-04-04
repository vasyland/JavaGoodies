package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DbUtils {

	private static final Logger log = LogManager.getLogger(DbUtils.class);
	
	public static Connection getConnection() throws SQLException {
		
		oracle.jdbc.pool.OracleDataSource ods = new oracle.jdbc.pool.OracleDataSource();

	    // Set the user name, password, driver type and network protocol
//	    ods.setServerName("localhost");
//	    ods.setPortNumber(1521);
//	    ods.setDriverType("thin");
//	    ods.setServiceName("xe");

	    ods.setUser("hr");
	    ods.setPassword("sea4me");

	    ods.setURL("jdbc:oracle:thin:@//localhost:1521/xe");
	    
	    return ods.getConnection();
	}
	
	
	public Properties getAppProperties() {
		Properties r =  new Properties();
		InputStream input  = null;
		try {
			input = DbUtils.class.getClassLoader().getResourceAsStream("../config/tutorials.properties");
			r.load(input);
			r.put("charSet", "UTF-8");
		} catch (NullPointerException npe) {
			log.error("tutorials.properties file not found.");
			//log.catching(npe);
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException ioe) {
					log.error(ioe.getMessage());
				}
			}
		}
		return r;
	}

	
	public static String generateActualSql(String sqlQuery, Object... parameters) {
		String[] parts = sqlQuery.split("\\?");
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < parts.length; i++) {
			String part = parts[i];
			sb.append(part);
			if(i < parameters.length) {
				sb.append(formatParameter(parameters[i]));
			}
		}
		return sb.toString();
	}
	
	
	private static String formatParameter(Object parameter) {
		if(parameter == null) {
			return "NULL";
		} else {
			if (parameter instanceof String) {
				return "'" + ((String) parameter).replace("'", "''") + "'";
			} else if (parameter instanceof Timestamp) {
				return "to_timestamp('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS")
						.format(parameter) +"', 'mm/dd/yyyy hh24:mi:ss')";
			} else if (parameter instanceof Date) {
				return "to_timestamp('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
						.format(parameter) +"', 'mm/dd/yyyy hh24:mi:ss')";
			} else if (parameter instanceof Boolean) {
				return ((Boolean) parameter).booleanValue() ? "1" : "0";
			} else {
				return parameter.toString();
			}
		}
	}
		
}

