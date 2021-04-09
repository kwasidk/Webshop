package nl.kattahkwasi.Main;

import java.sql.SQLException;
import java.util.Scanner;

import nl.kattahkwasi.Admin.Catergories;
import nl.kattahkwasi.Admin.Products;

public class Dashboard
{

    private static final Scanner scanner = new Scanner(System.in);

    public static void customerDashboard() {
        System.out.println("IT WORRRRKKSSSSS :) ");

        Boolean hasAccess = true;

        while (hasAccess) {

            int pageChoice = scanner.nextInt();
            switch (pageChoice) {
                case 1:
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Choice invalid, try again!");
                    continue;
            }

            hasAccess = false;
        }


    }

    public static void adminDashboard() throws SQLException {
        System.out.println("Welcome Admin, make a choice!\f");
        System.out.println("1. Go to categories menu ");
        System.out.println("2. Go to products menu ");
        Boolean hasAccess = true;

        while (hasAccess) {
            int pageChoice = scanner.nextInt();
            switch (pageChoice) {
                case 1:
                    Catergories.menuCategories();
                    break;
                case 2:
                    Products.menuProducts();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Choice invalid, try again!");
                    continue;
            }

            hasAccess = false;
        }
    }


}
