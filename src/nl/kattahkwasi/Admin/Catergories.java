package nl.kattahkwasi.Admin;

import nl.kattahkwasi.Main.App;
import java.sql.SQLException;
import java.util.Scanner;


public class Catergories
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuCategories() {

        boolean hasAccess = true;

        while (hasAccess) {

            System.out.println("1. Add categories\n2. Delete categories\n3.Edit categories");
            int pageChoices = scanner.nextInt();

            switch (pageChoices) {
                case 1:
                    addCategories();
                    break;
                case 2:
                    deleteCategories();
                    break;
                case 3:
                    editCategories();
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    continue;
            }

            hasAccess = false;
        }
    }


    public static void editCategories() {
        try {
            App.DATABASE.printCategories();

            System.out.println("Which category do you want to edit? Select the ID");
            int id = scanner.nextInt();

            System.out.println("Edit the category name");
            String name = scanner.next();

            App.DATABASE.editCategory(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCategories() {
        scanner.nextLine();

        try{
            App.DATABASE.printCategories();
            System.out.println("Which category do you want to delete? Select the ID");
            int id = scanner.nextInt();
            App.DATABASE.deleteCategory(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCategories() {
        scanner.nextLine();

        try {
            System.out.print("Add a categories: ");
            String category = scanner.nextLine();
            App.DATABASE.getAddCategories(category);
            System.out.println("You've successfully added a category");
            System.out.println();

            menuCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
