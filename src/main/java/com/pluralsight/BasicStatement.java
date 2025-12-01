package com.pluralsight;

import java.sql.*;

public class BasicStatement {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //IP Address
        //Internet Protocol

        //Address

        //DNS - Domain Name Service
        //brightspace.com -> 172.1.1.256:443

        //https -> port 443
        //http -> port 80

        //IP address -> location for a building

        //computers are buildings

        //ip:port

        //Example URL
        //https://yearup.brightspace.com/d2l/le/lessons/10273/topics/305203

        //localhost -> is our current computer
        //localhost resolves to IP 127.0.0.1

        //URL -> protocol://domain:port/path

        // 1. open a connection to the database
        // use the database URL to point to the correct database
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/northwind",
                "root",
                "yearup");


        // create statement
        // the statement is tied to the open connection
        Statement statement = connection.createStatement();
        // define your query
        String query = "SELECT CompanyName FROM Customers";
        // 2. Execute your query
        ResultSet results = statement.executeQuery(query);
        // process the results
        while (results.next()) {
            String name = results.getString("CompanyName");
            System.out.println(name);
        }

        // 3. Close the connection
        connection.close();


    }
}