package com.dubovik.library.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    private final static String HOST = "jdbc:mysql://localhost:3306/Library" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private final static String USER = "root";
    private final static String PASSWORD = "4ordCmax";

    public Connection connect() throws SQLException {
        Connection connect = DriverManager.getConnection(HOST, USER, PASSWORD);
        return connect;
    }
}
