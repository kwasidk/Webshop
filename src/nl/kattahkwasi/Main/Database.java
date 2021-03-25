package nl.kattahkwasi.Main;

import java.sql.*;
import java.util.Scanner;


public class Database
{

    private final Connection connection;
    private final static Scanner scanner = new Scanner(System.in);


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

    public void CreateNewUser(String firstName, String prefix, String lastName, String street, int housenumber, String postalcode, String city, String email, String password, String dateOfBirth) {

        String query = "INSERT INTO Klantengegevens(firstname, prefix, lastname, street, housenumber, postalcode, city, email, passwords, dateofbirth) " + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstName);
            stmt.setString(2, prefix);
            stmt.setString(3, lastName);
            stmt.setString(4, street);
            stmt.setInt(5, housenumber);
            stmt.setString(6, postalcode);
            stmt.setString(7, city);
            stmt.setString(8, email);
            stmt.setString(9, password);
            stmt.setString(10, dateOfBirth);
            stmt.executeUpdate();

            System.out.println("You've successfully registered!\nReturning to Homepage...");
            Menu.HomepageChoices();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUserInfo(String username, String password) throws SQLException {

        String dbUsername, dbPassword, dbRole;
        PreparedStatement stmt = connection.prepareStatement("SELECT email, password, roles FROM customer_info");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            dbUsername = rs.getString("email");
            dbPassword = rs.getString("password");
            dbRole = rs.getString("roles");

            if (dbUsername.equals(username)) {
                if (dbPassword.equals(password)) {
                    if (dbRole.equals("Admin")) {
                        System.out.println("Logging in as Admin...");
                        Dashboard.adminDashboard();
                        return true;
                    } else if (dbRole.equals("Customer")) {
                        System.out.println("Logging in");
                        Dashboard.customerDashboard();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void editCategory(int id, String category) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE categories SET category_name = ? WHERE ID = ?");
        stmt.setString(1, category);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    public void deleteCategory(int id) {
        String query = "DELETE FROM categories WHERE ID = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAddCategories(String categories) throws SQLException {

        String query = "INSERT INTO categories(category_name)" + "VALUE (?)";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("ID");
            String categoryName = rs.getString("category_name");

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, categories);
            stmt.executeUpdate();
        }
    }

    public void printCategories() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categories");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.printf("%d\t%s%n", rs.getInt("ID"), rs.getString("category_name"));
        }
    }

    public void loginHandler() throws SQLException {
        while (true) {
            System.out.println("Webshop BV Login\nEmail: ");
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (checkUserInfo(email, password)) {
                break;
            }
            System.out.println("Error, try again!");
        }
    }
}