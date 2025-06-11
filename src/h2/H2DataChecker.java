package h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DataChecker {
    // H2データベースをTCPモードで接続
    private static final String JDBC_URL = "jdbc:h2:~/scoremanager";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
//
    	System.out.println("test1");


        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

//        	System.out.println("test2");
            Statement stmt = conn.createStatement()) {


//
//    	try {

//    		InitialContext ic = new InitialContext();
//    		DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/scoremanager");

//    		Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
//
//    		System.out.println("test2");
//
//    		Statement stmt = conn.createStatement();
//
//        	System.out.println("test3");

            checkTable(stmt, "CLASS_NUM");
            checkTable(stmt, "SCHOOL");
            checkTable(stmt, "STUDENT");
            checkTable(stmt, "SUBJECT");
            checkTable(stmt, "TEACHER");
            checkTable(stmt, "TEST");

        	System.out.println("test4");


        } catch (SQLException e) {
        	System.out.println("cache");
            e.printStackTrace();
        }
    }

    private static void checkTable(Statement stmt, String tableName) throws SQLException {
        System.out.println("=== " + tableName + " ===");
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        while (rs.next()) {
            int columns = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columns; i++) {
                System.out.print(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " | ");
            }
            System.out.println();
        }
        rs.close();
    }
}

//package h2;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class H2DataChecker {
//    // H2データベース接続
//    private static final String JDBC_URL = "jdbc:h2:~/book";
////    private static final String JDBC_URL = "jdbc:h2:~/scoremanager";
//
//    private static final String USER = "sa";
//    private static final String PASSWORD = "";
//
//    public static void main(String[] args) {
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
//             Statement stmt = conn.createStatement()) {
//
//            checkTable(stmt, "product");
//            checkTable(stmt, "customer");
//            checkTable(stmt, "purchase");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void checkTable(Statement stmt, String tableName) throws SQLException {
//        System.out.println("=== " + tableName + " ===");
//        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
//        while (rs.next()) {
//            int columns = rs.getMetaData().getColumnCount();
//            for (int i = 1; i <= columns; i++) {
//                System.out.print(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " | ");
//            }
//            System.out.println();
//        }
//         rs.close();
//    }
//  }
