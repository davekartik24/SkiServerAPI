package dao;

import java.sql.*;
import org.apache.commons.dbcp2.*;

public class DBCPDataSource {

    private static BasicDataSource dataSource = new BasicDataSource();

    // NEVER store sensitive information below in plain text!
    private static final String HOST_NAME = System.getenv("MySQL_IP_ADDRESS");
    private static final String PORT = System.getenv("MySQL_PORT");
    private static final String DATABASE = "upic_ski_resorts";
    private static final String USERNAME = System.getenv("DB_USERNAME");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    static {
        // https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-jdbc-url-format.html
        String url = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", HOST_NAME, PORT, DATABASE);
        dataSource.setUrl(url);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return dataSource.getConnection();
    }
}
