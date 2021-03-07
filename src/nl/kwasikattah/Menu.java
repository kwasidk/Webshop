package nl.kwasikattah;

import java.sql.*;
import java.util.Scanner;

public class Menu
{


    private static final Scanner scanner = new Scanner(System.in);

    public void HomepageChoices() {

        System.out.println("Welcome to Webshop BV :)\nMake a choice.");
        System.out.println();
        System.out.println("1.Login\n2.Register");

        while (true) {

            int pageChoice = scanner.nextInt();
            switch (pageChoice) {
                case 1:
                    Login();
                    break;
                case 2:
                    RegisterUsers();
                    break;
                default:
                    System.out.println("Choice invalid, try again:\n1.Login\n2.Register");
            }
        }

    }

    public static void Login() {


    }

    public static void RegisterUsers() {

        scanner.nextLine();
        System.out.println("First name");
        String firstName = scanner.nextLine();

        System.out.println("Infix (If no infix press enter)");
        String infix = scanner.nextLine();

        System.out.println("Last Name");
        String lastName = scanner.nextLine();

        System.out.println("Street");
        String street = scanner.nextLine();

        System.out.println("House number");
        int housenumber = scanner.nextInt();

        scanner.nextLine();
        System.out.println("Postalcode");
        String postalcode = scanner.nextLine();

        System.out.println("City");
        String city = scanner.nextLine();

        System.out.println("Email");
        String email = scanner.nextLine();

        System.out.println("Password");
        String password = scanner.nextLine();

        System.out.println("Date of birth (enter it like this 2001-01-01)");
        String dateOfBirth = scanner.nextLine();

        App.DATABASE.CreateNewUser(firstName, infix, lastName, street, housenumber, postalcode, city, email, password, dateOfBirth);

    }

}
