package org.example;

import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String pass = "Obi1kenobi";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS empleados (" +
                "id SERIAL PRIMARY KEY," +
                "nombre VARCHAR(255)," +
                "apellido VARCHAR(255)," +
                "dni VARCHAR(10) UNIQUE," +
                "depto VARCHAR(255));";

        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.execute();
        }
    }
}

