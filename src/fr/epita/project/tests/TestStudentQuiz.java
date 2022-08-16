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
            logMessage("Would you like to create and account 😬");
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
        int current_student_id;
        int student_score = 0;
        String topic;
        String current_student_name;
        int total_questions;
        String question_type;
        logMessage("Hi 👋🏽" + " " + student_data.get(0).getName());
        logMessage("..... Lets get QUIZZINGG 🧠 .....");
        logMessage("......................................\n");
//        current_student_name = student_data.get(0).getName();
//        current_student_id = student_data.get(0).getId();

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


        logMessage("What question type are you interested in ( mcq or associative or open_question ) \n......................................");
        question_type = getInput(scanner);
        if (!question_type.matches("mcq|associative|open_question")) {
            logMessage("...........Invalid Option selected ❌..................");
            System.exit(0);
        }

        System.out.println(student_score);


        //get question from db
        List<Question> students_questions = QuestionDao.getQuestion(difficulty_level, topic, question_type);
        total_questions = students_questions.size();

        if (question_type.matches("mcq")) {
            logMessage("Type in the correct answer from the list of options: | \n Valid answers are a, b or c. ONLY!!!\n");
        }

        for (Question q : students_questions) {
            int question_id = q.getId();

            //if question type is mcq
            if (question_type.matches("mcq")) {
                handleMCQQuestions(scanner, student_score, q, question_id);
            }

            //checking if the question is an open question
            if (question_type.matches("open_question")) {
                handleOpenQuestions(scanner, student_score, q, question_id);
            }

        }
        logMessage(".............................\n");
        logMessage(String.valueOf("Your SCORE:👨🏽‍🏫.....:" + student_score + "/" + total_questions));
        System.exit(0);


    }

    private static void handleMCQQuestions(Scanner scanner, int student_score, Question q, int question_id) throws SQLException {
        List<MCQQuestion> answer_options = QuestionDao.getMCQQuestion(question_id);

        String ques = q.getQuestion();
        String answer = answer_options.get(0).getAnswer();
        String[] options = answer_options.get(0).getOptions();

        logMessage("...........Preparing Question 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
        logMessage("QUESTION:" + ques);
        logMessage("ANSWER:" + answer);
        logMessage("OPTIONS:👇🏾");
        for (String a : options) {
            logMessage(a);
        }
//                            logMessage("ANSWER:" + answer);
        logMessage("...........waiting for answer 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
        String get_answer = scanner.nextLine();
        if (answer.equals(get_answer)) {
            student_score++;
        }

        logMessage(String.valueOf(student_score));

    }

    private static void handleOpenQuestions(Scanner scanner, int student_score, Question q, int question_id) throws SQLException {
        List<OpenQuestion> answers = QuestionDao.getOpenQuestion(question_id);

        String ques = q.getQuestion();
        String answer = answers.get(0).getAnswer();
        logMessage("ANSWER:" + answer);
        logMessage("...........Preparing Question 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
        logMessage("QUESTION:" + ques);
        logMessage("...........waiting for answer 𝌗 𝌗 𝌗 𝌗 𝌗..................\n");
        String get_answer = scanner.nextLine();
        if (answer.equals(get_answer)) {
            student_score++;
            System.out.println("output:" + student_score);

        }
    }
}
