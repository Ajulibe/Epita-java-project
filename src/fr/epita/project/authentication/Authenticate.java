package fr.epita.project.authentication;

import fr.epita.project.datamodel.Admin;
import fr.epita.project.services.AdminDao;
import fr.epita.project.tests.TestStudentQuiz;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static fr.epita.project.services.Utilities.getInput;
import static fr.epita.project.services.Utilities.logMessage;

public class Authenticate {

    static int student_score = 0;

    public static void checkPermissions(Scanner scanner, String userResponse) throws SQLException, FileNotFoundException {
        String password;
        String username;
        while (!"q".equals(userResponse)) {
            userResponse = scanner.nextLine();
            switch (userResponse) {
                case "1":
                    TestStudentQuiz.validateStudent(scanner);
                    break;

                case "2":
                    //Admin
                    logMessage("Please enter your admin credentials:");
                    logMessage("Enter your username:");
                    username = getInput(scanner);

                    logMessage("Enter your password:");
                    password = getInput(scanner);

                    List<Admin> admin_data = AdminDao.validateAdmin(username, password);

                    boolean isEmpty = admin_data.isEmpty();

                    if (isEmpty) {
                        logMessage("Hi" + " " + username + "\n" + "We couldn't find you in our Database ☹️");
                        System.exit(0);
                        logMessage(".............................\n");
                    } else {

                        logMessage("Hi Admin " + username);
                        logMessage("what would you like to do?");
                        logMessage("a). Create a question");
                        logMessage("b). Update a question");
                        logMessage("c). Delete a question");

                        String decision = getInput(scanner);

                        if (decision.matches("a")) {
                            createAQuestion(scanner);
                        }

                        if (decision.matches("b")) {
                            logMessage("What is the id of the question you want to update");
                            int question_id = Integer.parseInt(getInput(scanner));

                            logMessage("Whats the new difficulty you want to set e.g (1, 2, 3)");
                            int new_difficulty = Integer.parseInt(getInput(scanner));

                            AdminDao.updateQuestionDifficulty(question_id, new_difficulty);
                            logMessage("...........Successfully deleted ✅..................");
                        }

                        if (decision.matches("c")) {
                            logMessage("What is the id of the question you want to delete");
                            int question_id = Integer.parseInt(getInput(scanner));
                            AdminDao.deleteAQuestion(question_id);
                            logMessage("...........Successfully Updated ✅..................");
                        }
                    }

                    break;
                case "q":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private static void createAQuestion(Scanner scanner) throws SQLException, FileNotFoundException {
        logMessage("...........creating a question 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
        //create question
        logMessage("Please enter the name of the question");
        String question_name = getInput(scanner);

        logMessage("Please enter the type of the question (e.g mcq, open_question, associative)");
        String question_type = getInput(scanner);
        if (!question_type.matches("mcq|open_question|associative")) {
            logMessage("...........Wrong input 😬..................");
            System.exit(0);
        }

        System.out.println("question_type:" + question_type);
//
        logMessage("Please enter the desired Topic of the question (e.g java , code)");
        logMessage("If plenty, please separate with a comma (e.g java , code, compile, algebra)");
        String topic = getInput(scanner);
        logMessage("Please enter the desired difficulty for the question (e.g. 1, 2, 3)");
        int difficulty = Integer.parseInt(getInput(scanner));

        AdminDao.createQuestion(question_name, topic, question_type, difficulty);

        if (question_type.matches("mcq")) {
            String answer = "";
            String answers = ensureRightOptions(scanner, answer);
            String no_of_options_string = "";
            int no_of_options = 0;
            int no_of_loops = ensureNumberOfOptions(scanner, no_of_options_string, no_of_options);
            StringBuilder optionsbuilder = new StringBuilder(" ");

            for (int i = 1; i <= no_of_loops; i++) {
                logMessage("Enter option" + " " + i + " " + "along with its corresponding alphabet? (e.g a). true)");
                String option = getInput(scanner) + ", ";
                optionsbuilder.append(option);
            }

            String final_options = String.valueOf(optionsbuilder);

            AdminDao.addMCQQuestion(answers, new String[]{final_options});
            logMessage("...........Successfully added ✅..................");
            System.exit(0);
        }

        if (question_type.matches("associative")) {
            logMessage("...........Not Available for now ⛔️..................");
            System.exit(0);
        }

        if (question_type.matches("open_question")) {
            logMessage("Type in the answer to your question");
            String answer = getInput(scanner);
            AdminDao.addOpenQuestion(answer);
            logMessage("...........Successfully added ✅..................");
            System.exit(0);
        }
    }

    private static String ensureRightOptions(Scanner scanner, String answer) {
        logMessage("Please enter the answer to the question, it can either be a, b, c, true, or false");
        answer = getInput(scanner);
        if (!answer.matches("a|b|c|true|false")) {
            logMessage("...........Please try again 😬..................");
            ensureRightOptions(scanner, answer);
        }
        return answer;
    }

    private static int ensureNumberOfOptions(Scanner scanner, String no_of_options_string, int no_of_options) {
        logMessage("How many options are you suggesting? (e.g 1,2 or 3)");
        no_of_options_string = getInput(scanner);
        no_of_options = Integer.parseInt(no_of_options_string);
        if (!no_of_options_string.matches("1|2|3|")) {
            logMessage("...........Please try again 😬..................");
            ensureNumberOfOptions(scanner, no_of_options_string, no_of_options);
        }

        return no_of_options;
    }
}
