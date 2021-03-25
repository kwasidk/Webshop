package nl.kattahkwasi.Main;

import java.util.Scanner;
import nl.kattahkwasi.Admin.Catergories;

public class Dashboard
{

    private static final Scanner scanner = new Scanner(System.in);

    public static void customerDashboard(){
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
                    System.out.println("Choice invalid, try again:\n1.Login\n2.Register");
            }

            hasAccess = false;
        }


    }

    public static void adminDashboard(){
        System.out.println("Welcome Admin, make a choice!\f");
        System.out.print("1. View Categories: ");
        Boolean hasAccess = true;

        while (hasAccess) {
            int pageChoice = scanner.nextInt();
            switch (pageChoice) {
                case 1:
                    Catergories.menuCategories();
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



}
