package fr.epita.project.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Utilities {
    public static void logMessage(String message) {
        System.out.println(message);
    }

    //connects to the database
    public static Connection connectToDb() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/epita-project", "postgres", "postgres");
    }

    /**
     * @param scanner  input scanner
     * @return string from the console
     */
    public static String getInput(Scanner scanner) {
        return scanner.nextLine();
    }


}
