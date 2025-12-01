package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = "root";
        String password = "yearup";


        String query = "SELECT * FROM Products";
        try {
            // Establishing connection
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query);








            // Executing query
            ResultSet results = statement.executeQuery();

            // Processing the result set
            while (results.next()) {

                System.out.println("-------------------------");
                System.out.println("Product ID: " + results.getString("ProductID"));
                System.out.println("Product Name: " + results.getString("ProductName"));
                System.out.println("Unit Price: " + results.getString("UnitPrice"));
                System.out.println("Units In Stock: " + results.getString("UnitsInStock"));
                System.out.println("-------------------------");
            }

            // Closing resources
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}