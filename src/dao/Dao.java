package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class Dao {
	static DataSource ds;

	public Connection getConnection()throws Exception {

		if (ds == null) {
			InitialContext ic = new InitialContext();

			ds = (DataSource) ic.lookup("java:comp/env/jdbc/book");
		}
		return ds.getConnection();
	}

	 protected void close(Connection connection, PreparedStatement ps, ResultSet rset) {
	        try {
	            if (rset != null) rset.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
	            if (ps != null) ps.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }



}


