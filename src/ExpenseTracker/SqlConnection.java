package ExpenseTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnection {
    private final String USER_NAME = "root";
    private final String PASSWORD = "karaaslan1905@@";
    
    private final String SERVER_URL = "jdbc:mysql://localhost:3306/";
    private final String DB_NAME = "expense_tracker";
    
    public Connection getConnection() throws SQLException {
        try {
            Connection baseConn = DriverManager.getConnection(SERVER_URL, USER_NAME, PASSWORD);
            Statement stmt = baseConn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.close();
            baseConn.close();

            Connection conn = DriverManager.getConnection(SERVER_URL + DB_NAME, USER_NAME, PASSWORD);
            createTableIfNotExists(conn);
            return conn;
            
        } catch (SQLException e) {
            System.err.println("Bağlantı veya Başlatma Hatası: " + e.getMessage());
            throw e;
        }
    }

    private void createTableIfNotExists(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS expenses ("
                   + "id INT AUTO_INCREMENT PRIMARY KEY, "
                   + "category VARCHAR(50) NOT NULL, "
                   + "price DECIMAL(10,2) NOT NULL, "
                   + "description VARCHAR(999) DEFAULT NULL, "
                   + "expense_date DATE NOT NULL DEFAULT (CURRENT_DATE)"
                   + ") ENGINE=InnoDB;";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Tablo oluşturulamadı: " + e.getMessage());
        }
    }
}