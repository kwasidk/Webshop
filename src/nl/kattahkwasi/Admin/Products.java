package nl.kattahkwasi.Admin;

import nl.kattahkwasi.Main.App;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class Products
{

    private static final Scanner scanner = new Scanner(System.in);

    public static void menuProducts() throws SQLException {
        App.DATABASE.getProductInfo("1");
        System.out.print("1. Add products:\n2.Edit Products:\n3.Delete Products:");
        int pageChoice = scanner.nextInt();
        boolean hasAccess = true;
        while (hasAccess){
            switch (pageChoice) {
                case 1:
                    addProducts();
                    break;
                case 2:
                   editProducts();
                case 3:
                    deleteProducts();
                    break;
                default:
                    System.out.println("Invaild input, try again!");
                    continue;
            }
            hasAccess = false;
        }
    }


    public static void addProducts() throws SQLException {
        scanner.nextLine();

        System.out.println("");
        App.DATABASE.printCategories();


        System.out.print("Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Product Description: ");
        String productDescription = scanner.nextLine();

        System.out.print("Product Price: ");
        BigDecimal productPrice = scanner.nextBigDecimal();

        System.out.print("Categories: ");
        int categories = scanner.nextInt();



        App.DATABASE.addProducts(productName, productDescription, productPrice, categories);

    }


    public static void deleteProducts() throws SQLException{
        scanner.nextLine();

        App.DATABASE.printProducts();

        System.out.println("Which product do you want to delete (enter the ID)");
        int id = scanner.nextInt();


        App.DATABASE.deleteProducts(id);


    }

    public static void editProducts() throws SQLException {
        scanner.nextLine();

        App.DATABASE.printProducts();

        System.out.println("Which product do you want to edit (enter the ID) ");
        String id = scanner.nextLine();

        if (id.equals(null))
            return;

       var product = App.DATABASE.getProductInfo(id);
        System.out.println("Which ID do you want to change?");

        String editID = scanner.nextLine();

        System.out.println("Set new value");
        String newValue = scanner.nextLine();

        product.set(Integer.parseInt(editID), newValue);

        System.out.println(product.get(Integer.parseInt(editID)));

        App.DATABASE.editProduct(product);

    }


}
