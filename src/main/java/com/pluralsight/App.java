package com.pluralsight;

import com.mysql.cj.Query;

import java.sql.*;
import java.util.Scanner;

public class App {
    static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = args[0];
        String password = args[1];



        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet results = null;

        try {
            connection = DriverManager.getConnection(url, user, password);

            while (true) {
                menu();
                String query = queryChoice();

                if (query.isBlank()) {
                    break;
                }

                statement = connection.prepareStatement(query);
                results = statement.executeQuery();


                if (query.equals(queryProducts())) {

                    while (results.next()) {
                        System.out.println("-------------------------");
                        System.out.println("Product ID: " + results.getString("ProductID"));
                        System.out.println("Product Name: " + results.getString("ProductName"));
                        System.out.println("Unit Price: " + results.getString("UnitPrice"));
                        System.out.println("Units In Stock: " + results.getString("UnitsInStock"));
                        System.out.println("-------------------------");
                    }
                } else if (query.equals(queryCustomers())) {

                    while (results.next()) {
                        System.out.println("-------------------------");
                        System.out.println("Contact Name: " + results.getString("ContactName"));
                        System.out.println("Company Name: " + results.getString("CompanyName"));
                        System.out.println("City: " + results.getString("City"));
                        System.out.println("Country: " + results.getString("Country"));
                        System.out.println("Phone: " + results.getString("Phone"));
                        System.out.println("-------------------------");
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void menu(){
        System.out.println("What do you want to do?");
        System.out.println("\t1) Display all products");
        System.out.println("\t2) Display all Customers");
        System.out.println("\t0) Exit");




    }

    private static String getUserInput(String prompt){
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

  private static String queryProducts(){
        return "SELECT * FROM Products";
    }

    private static String queryCustomers(){
        return "SELECT * FROM Customers ORDER BY Country";
    }

    private static String queryChoice(){
        int choice = Integer.parseInt(getUserInput("Select an option"));
        String query = "";
        switch (choice){
            case 1:
                query = queryProducts();
                break;
            case 2:
                query = queryCustomers();
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println("Invalid option");
        }

        return query;
    }



    private static void exit(){
        System.out.println("Goodbye!");
        System.exit(0);
    }


}