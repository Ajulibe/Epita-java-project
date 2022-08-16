package fr.epita.project.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

import org.apache.ibatis.jdbc.ScriptRunner;

import static fr.epita.project.services.Utilities.connectToDb;

public class SeedDB {
    public static void runSeed() throws SQLException, FileNotFoundException {
        Connection connection = connectToDb();

        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(connection);

        //delete table
        Reader deleteAllTables = new BufferedReader(new FileReader("./resources/delete_statements.sql"));
        sr.runScript(deleteAllTables);

        //create tables
        Reader createAnswersTable = new BufferedReader(new FileReader("./resources/create_statements.sql"));
        sr.runScript(createAnswersTable);

        //seed tables with data
        Reader insertSeed = new BufferedReader(new FileReader("./resources/insert_statements.sql"));
        sr.runScript(insertSeed);

        connection.close();
    }


}
