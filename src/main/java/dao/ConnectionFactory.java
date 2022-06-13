package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    static private String url = "jdbc:postgresql://localhost/web-shop";
    static private Properties properties = new Properties();
    static {
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "sa");
        //properties.setProperty("ssl", "true");
    }
    static private Connection connection;

    private ConnectionFactory(){}

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, properties);
        return connection;
    }
}
