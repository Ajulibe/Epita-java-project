package fr.epita.project.launcher;

import fr.epita.project.authentication.Authenticate;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;
;
import static fr.epita.project.services.SeedDB.runSeed;
import static fr.epita.project.services.Utilities.logMessage;



//this is an entirely console based quiz application. questions and usage rules
//are stated explicity in the console also
public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
//        runSeed();
        logMessage(".....Welcome to the quiz manager 🧠 .....\n");
        logMessage("Who are you ? ... \n 1). Student 👨🏾‍🎓 \n 2). Admin 👨🏽‍💼\n ");
        logMessage("Your Input should be 1 or 2 \n");

        String userResponse = "";
        Authenticate.checkPermissions(scanner, userResponse);
    }

}
