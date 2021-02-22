package nl.kwasikattah;

import java.sql.*;

public class ConnectionDB
{
    public static void Database() {
        String url = "jdbc:mysql://localhost:3306/Webshop";
        String username = "root";
        String password = "root";

        System.out.println("Connecting Database... ");
        try {

            // Trying to connect with the Database
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected");

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect with database!", e);
        }

    }
}
