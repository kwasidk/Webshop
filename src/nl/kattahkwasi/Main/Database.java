package nl.kattahkwasi.Main;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
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

    public void loginHandler() throws SQLException {
        while (true) {
            System.out.println("Webshop BV Login");
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


    public void editCategory(int id, String category) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE categories SET name = ? WHERE categoriesID = ?");
        stmt.setString(1, category);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    public void deleteCategory(int id) throws SQLException {
        String query = "DELETE FROM categories WHERE categoriesID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();

    }

    public void addCategory(String categories) throws SQLException {

        String query = "INSERT INTO categories(name)" + "VALUE (?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, categories);
        stmt.executeUpdate();

    }

    public void printCategories() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categories");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.printf("%d\t%s%n", rs.getInt("categoriesID"), rs.getString("name"));
        }
    }


    public void addProducts(String productName, String productDescription, BigDecimal productPrice, int categories) throws SQLException {

//        String query = "SELECT * FROM products inner join products_categories pc on products.productsID = pc.fk_product_ID AND categories.categoriesID = pc.fk_categories_ID";


        String query = " INSERT INTO products(name, description, price, categoriesID)" + "VALUES (?,?,?,?)";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, productName);
        stmt.setString(2, productDescription);
        stmt.setBigDecimal(3, productPrice);
        stmt.setInt(4, categories);
        stmt.executeUpdate();

    }


    public void editProduct(ArrayList product) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE products SET productsID = ?, name = ?, description = ?, price = ?, categoriesID = ? WHERE productsID = ?");
        stmt.setInt(1, Integer.parseInt(String.valueOf(product.get(0))));
        stmt.setString(2, String.valueOf(product.get(1)));
        stmt.setString(3, String.valueOf(product.get(2)));
        stmt.setBigDecimal(4, BigDecimal.valueOf(Double.parseDouble(String.valueOf(product.get(3)))));
        stmt.setInt(5, Integer.parseInt(String.valueOf(product.get(4))));
        stmt.setInt(6, Integer.parseInt(String.valueOf(product.get(0))));
        stmt.executeUpdate();

    }

    public ArrayList getProductInfo(String id) throws SQLException {

        ArrayList<String> productProperties = new ArrayList<>();


        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM products WHERE productsID = ?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            //System.out.printf("%d\t%s%n",rs.getInt("productsID"), rs.getString("name"), rs.getString("description"), rs.getBigDecimal("price"), rs.getInt("categoriesID"));
            System.out.println("0. " + rs.getInt("productsID"));
            System.out.println("1. " + rs.getString("name"));
            System.out.println("2. " + rs.getString("description"));
            System.out.println("3. " + rs.getBigDecimal("price"));
            System.out.println("4. " + rs.getInt("categoriesID"));

            productProperties.add(rs.getInt("productsID") + "");
            productProperties.add(rs.getString("name") + "");
            productProperties.add(rs.getString("description") + "");
            productProperties.add(rs.getBigDecimal("price") + "");
            productProperties.add(rs.getInt("categoriesID") + "");


        }



        return productProperties;

    }



    public void deleteProducts(int id) throws SQLException{
        String query = "DELETE FROM products WHERE productsID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();



    }

    public void printProducts() throws SQLException{
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM products");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            //System.out.printf("%d\t%s%n",rs.getInt("productsID"), rs.getString("name"), rs.getString("description"), rs.getBigDecimal("price"), rs.getInt("categoriesID"));
            System.out.println(rs.getInt("productsID"));
            System.out.println(rs.getString("Name"));
            System.out.println(rs.getString("description"));
            System.out.println(rs.getBigDecimal("price"));
            System.out.println(rs.getString("categoriesID"));


        }
    }



}

