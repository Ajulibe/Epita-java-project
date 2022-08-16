package fr.epita.project.tests;

import fr.epita.project.datamodel.MCQQuestion;
import fr.epita.project.datamodel.OpenQuestion;
import fr.epita.project.datamodel.Question;
import fr.epita.project.datamodel.Student;
import fr.epita.project.services.QuestionDao;
import fr.epita.project.services.StudentsDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static fr.epita.project.services.Utilities.getInput;
import static fr.epita.project.services.Utilities.logMessage;

public class TestStudentQuiz {


    public static void validateStudent(Scanner scanner) throws SQLException {
        logMessage("You have Selected the Student Option \n......................................\n");
        logMessage("Please enter your username 👮🏽‍");
        String name_input = scanner.nextLine();
        logMessage(".............................\n");

        //fetch the student from the DB
        List<Student> student_data = StudentsDao.getStudent(name_input);

        //check if the student exists
        boolean isEmpty = student_data.isEmpty();

        if (isEmpty) {
            logMessage("Hi" + " " + name_input + "\n" + "We couldn't find you in our Database ☹️");
            logMessage("Would you like to create an account 😬");
            logMessage("yes or no");
            String create_account = getInput(scanner);
            logMessage(".............................\n");
            if (create_account.equals("yes")) {
                logMessage("please type in a chosen username 😌");
                String user_name = getInput(scanner);
                logMessage(".............................\n");
                List<Student> data = StudentsDao.createStudent(user_name);
                logMessage("...........processing 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
                logMessage("Welcome onboard user_name 🖖🏾 ...\n");
                processQuiz(scanner, data);
            } else {
                logMessage("See you soon 🖖🏾 ...\n");
                System.exit(0);
            }
        } else {
            processQuiz(scanner, student_data);
        }

    }

    private static void processQuiz(Scanner scanner, List<Student> student_data) throws SQLException {
        String difficulty_level;
        String topic;
        int total_questions;
        String question_type;
        logMessage("Hi 👋🏽" + " " + student_data.get(0).getName());
        logMessage("..... Lets get QUIZZINGG 🧠 .....");
        logMessage("......................................\n");
        logMessage("What difficulty level do you want? ( 1, 2 or 3 ) \n");
        difficulty_level = getInput(scanner);
        if (!difficulty_level.matches("1|2|3")) {
            logMessage("...........Invalid Option selected ❌..................");
            System.exit(0);
        }


        logMessage("What topic are you interested in? ( java or code ) \n......................................");
        topic = getInput(scanner);
        if (!topic.matches("java|code")) {
            logMessage("...........Invalid Option selected ❌..................");
            System.exit(0);
        }


        logMessage("What question type are you interested in ( mcq or open_question or associative ) \n......................................");
        question_type = getInput(scanner);
        if (!question_type.matches("mcq|associative|open_question")) {
            logMessage("...........Invalid Option selected ❌..................");
            System.exit(0);
        }


        //get question from db
        List<Question> students_questions = QuestionDao.getQuestion(difficulty_level, topic, question_type);
        total_questions = students_questions.size();

        if (question_type.matches("mcq")) {
            logMessage("Type in the correct answer from the list of options: | \n Valid answers are a, b or c. ONLY!!!\n");
        }

        int student_score = 0;
        for (Question q : students_questions) {
            int question_id = q.getId();
            //if question type is mcq
            if (question_type.matches("mcq")) {
                String status = handleMCQQuestions(scanner, q, question_id);
                if (status.equals("correct")) {
                    student_score++;
                }
            }
            //checking if the question is an open question
            if (question_type.matches("open_question")) {
                String status = handleOpenQuestions(scanner, q, question_id);
                if (status.equals("correct")) {
                    student_score++;
                }
            }
        }
        logMessage(".............................\n");
        logMessage("Your SCORE:👨🏽‍🏫.....:" + student_score + "/" + total_questions);
        System.exit(0);
    }

    private static String handleMCQQuestions(Scanner scanner, Question q, int question_id) throws SQLException {
        List<MCQQuestion> answer_options = QuestionDao.getMCQQuestion(question_id);
        String ques = q.getQuestion();
        String answer = answer_options.get(0).getAnswer();
        String[] options = answer_options.get(0).getOptions();
        String tracker = "";
        logMessage("...........Preparing Question 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
        logMessage("QUESTION:" + ques);
        logMessage("ANSWER:" + answer);
        logMessage("OPTIONS:👇🏾");
        for (String a : options) {
            logMessage(a);
        }
        logMessage("...........waiting for answer 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
        String get_answer = getInput(scanner);
        if (answer.matches(get_answer)) {
            tracker = "correct";
        } else {
            tracker = "wrong";
        }
        return tracker;
    }

    private static String handleOpenQuestions(Scanner scanner, Question q, int question_id) throws SQLException {
        List<OpenQuestion> answers = QuestionDao.getOpenQuestion(question_id);
        String tracker = "";
        String ques = q.getQuestion();
        String answer = answers.get(0).getAnswer();
        logMessage("...........Preparing Question 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
        logMessage("QUESTION:" + ques);
        logMessage("...........waiting for answer 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
        String get_answer = getInput(scanner);
        if (answer.matches(get_answer)) {
            tracker = "correct";
        } else {
            tracker = "wrong";
        }
        return tracker;
    }
}
