package com.pluralsight;

import com.mysql.cj.Query;

import java.net.ConnectException;
import java.sql.*;
import java.util.Scanner;

public class App {
    static final Scanner scanner = new Scanner(System.in);
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://127.0.0.1:3306/northwind";

    public static void main(String[] args) {
        String user = args[0];
        String password = args[1];

        loadConnection(user, password);
        run();


    }


    private static void menu() {
        System.out.println("What do you want to do?");
        System.out.println("\t1) Display all products");
        System.out.println("\t2) Display all Customers");
        System.out.println("\t3) Display all Categories ");
        System.out.println("\t0) Exit");


    }

    private static String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    private static int getUserIntInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return Integer.parseInt(input);

    }

    private static void queryProducts() {
        String query = "SELECT * FROM Products";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery()) {


            while (results.next()) {
                System.out.println("-------------------------");
                System.out.println("Product ID: " + results.getString("ProductID"));
                System.out.println("Product Name: " + results.getString("ProductName"));
                System.out.println("Unit Price: " + results.getString("UnitPrice"));
                System.out.println("Units In Stock: " + results.getString("UnitsInStock"));
                System.out.println("-------------------------");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void queryCategories() {
        String query = "SELECT * FROM Categories";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery()) {

            while (results.next()) {
                System.out.println("-------------------------");
                System.out.println("Category ID: " + results.getString("CategoryID"));
                System.out.println("Category Name: " + results.getString("CategoryName"));
                System.out.println("Description: " + results.getString("Description"));
                System.out.println("-------------------------");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void categoryIdQuestion() {
        int categoryId = getUserIntInput("Enter a category ID to Display products: ");
        String query = "SELECT * FROM Products WHERE CategoryID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    System.out.println("-------------------------");
                    System.out.println("Product ID: " + results.getString("ProductID"));
                    System.out.println("Product Name: " + results.getString("ProductName"));
                    System.out.println("Unit Price: " + results.getString("UnitPrice"));
                    System.out.println("Units In Stock: " + results.getString("UnitsInStock"));
                    System.out.println("-------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void queryCustomers() {

        String query = "SELECT * FROM Customers ORDER BY Country";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet results = statement.executeQuery()) {


            while (results.next()) {
                System.out.println("-------------------------");
                System.out.println("Contact Name: " + results.getString("ContactName"));
                System.out.println("Company Name: " + results.getString("CompanyName"));
                System.out.println("City: " + results.getString("City"));
                System.out.println("Country: " + results.getString("Country"));
                System.out.println("Phone: " + results.getString("Phone"));
                System.out.println("-------------------------");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void run() {
        while (true) {
            menu();
            switch (getUserIntInput("Select an option: ")) {
                case 1:
                    queryProducts();
                    break;
                case 2:
                    queryCustomers();
                    break;
                case 3:
                    queryCategories();
                    categoryIdQuestion();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }

    public static void loadConnection(String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error when loading connection; Restart app to try again.");
            e.printStackTrace();
        }
    }


    private static void exit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }


}