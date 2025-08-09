package com.nagaraju.eBanking.ui.engine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static final Logger LOGGER = Logger.getLogger(DBManager.class.getName());

    private static final String DB_URL = "jdbc:mysql://localhost:3306/bankmsdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private Connection connection;

    // Open DB connection
    public void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            LOGGER.info("Database connection established.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database connection failed: " + e.getMessage());
        }
    }

    // Close DB connection
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Database connection closed.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error closing database connection: " + e.getMessage());
        }
    }

    // Execute SELECT query
    public List<String[]> executeSelect(String query) {
        List<String[]> resultList = new ArrayList<>();
        try {
        	Statement stmt = connection.createStatement(); 
    		ResultSet rs = stmt.executeQuery(query); 
            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                String[] rowData = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getString(i);
                }
                resultList.add(rowData);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Query execution failed: " + e.getMessage(), e);
        }
        return resultList;
    }
    public void printResults(List<String[]> data) {
        for (String[] row : data) {
            System.out.println(String.join(" | ", row));
        }
    }

    // Execute INSERT/UPDATE/DELETE
    public boolean executeUpdate(String query) {
        try (Statement stmt = connection.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            LOGGER.info("Query executed successfully. Rows affected: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Update execution failed: " + e.getMessage(), e);
            return false;
        }
    }
    
    public static void main(String[] args) {
    	DBManager dbManager = new DBManager();
    	dbManager.connect();
    	List<String[]> data = dbManager.executeSelect("SELECT * FROM `tblpayee`");
    	String userName = data.get(0)[1].toString();
//    	System.out.println("User Name  :"+userName);
//    	System.out.println("Account number : "+data.get(0)[0].toString());
    	dbManager.printResults(data);
    	dbManager.disconnect();
    	
	}
}
