package dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class ConnectionFactoryPostgres implements ConnectionFactory {
    static private String url = "jdbc:postgresql://localhost/web-shop";
    static private Properties properties = new Properties();
    static {
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "sa");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, properties);
    }
}
