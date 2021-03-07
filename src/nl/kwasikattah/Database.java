package nl.kwasikattah;

import java.sql.*;
import java.util.Set;

public class Database
{

    private final Connection connection;
    private final static String sql = "INSERT INTO Klantengegevens(Voornaam, Tussenvoegsel, Achternaam, Straat, Huisnummer, Postcode, Woonplaats, Email, Wachtwoord, Geboortedatum) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?)";

    public Database(String url, String username, String password) {
        System.out.println("Connecting Database... ");
        try {

            // Trying to connect with the Database
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected");
            System.out.println("\f");

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect with database!", e);
        }

    }

    public void CreateNewUser(String firstName, String prefix, String lastName, String street, int housenumber, String zipcode, String city, String email, String password, String dateOfBirth) {

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, prefix);
            stmt.setString(3, lastName);
            stmt.setString(4, street);
            stmt.setInt(5, housenumber);
            stmt.setString(6, zipcode);
            stmt.setString(7, city);
            stmt.setString(8, email);
            stmt.setString(9, password);
            stmt.setString(10,dateOfBirth);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
