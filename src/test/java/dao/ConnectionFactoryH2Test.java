package dao;

import org.h2.tools.RunScript;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class ConnectionFactoryH2Test implements ConnectionFactory{

    private String url = "jdbc:h2:mem:test";
    private Properties properties;

    private Connection neverClosingConnection;

    ConnectionFactoryH2Test() {
        properties = new Properties();
        properties.setProperty("user", "sa");
        properties.setProperty("password", "");
        try {
            neverClosingConnection = getConnection();
            RunScript.execute(neverClosingConnection, new FileReader("src/test/resources/create.sql"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            neverClosingConnection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, properties);
        return connection;
    }
}
