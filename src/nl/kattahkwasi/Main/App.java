package nl.kattahkwasi.Main;

import nl.kattahkwasi.Admin.*;

import java.sql.SQLException;

public class App
{
    private static final String url = "jdbc:mysql://localhost:3306/Webshop";
    private static final String username = "root";
    private static final String password = "root";
    public static final Database DATABASE = new Database(url, username, password);

    public static void main(String[] args) throws SQLException {

        Menu menu = new Menu();
        menu.HomepageChoices();

        new Dashboard();
    }

}
