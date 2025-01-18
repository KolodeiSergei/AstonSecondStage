package org.example;


import java.sql.DriverManager;
import java.sql.SQLException;

public class App
{
    public static void main(String[] args) throws SQLException {
        DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "mugivara25");
    }
}
