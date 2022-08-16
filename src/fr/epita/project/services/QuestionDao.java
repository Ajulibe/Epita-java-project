package fr.epita.project.services;

import fr.epita.project.datamodel.MCQQuestion;
import fr.epita.project.datamodel.OpenQuestion;
import fr.epita.project.datamodel.Question;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fr.epita.project.services.Utilities.connectToDb;

public class QuestionDao {

    public static List<Question> getQuestion(String difficulty, String topic, String question_type) throws SQLException {
        int difficulty_conv = Integer.parseInt(difficulty);
        Connection connection = connectToDb();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM questions WHERE topics LIKE ? AND difficulty = ? AND question_type = ?");
        preparedStatement.setString(1, "%" + topic + "%");
        preparedStatement.setInt(2, difficulty_conv);
        preparedStatement.setString(3, question_type);

        getQueryResults(preparedStatement);
        List<Question> queried_questions = getQueryResults(preparedStatement);

        connection.close();

        return queried_questions;
    }

    private static List<Question> getQueryResults(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        String question;
        int difficulty;
        int id;
        String topic;
        String[] topics;
        String question_type;

        List<Question> questions = new ArrayList<>();
        while (resultSet.next()) {
            try {
                question = resultSet.getString("question");
                topic = resultSet.getString("topics");
                topics = topic.split(",");
                id = resultSet.getInt("id");
                difficulty = resultSet.getInt("difficulty");
                question_type = resultSet.getString("question_type");
                questions.add(new Question(id, question, difficulty, topics, question_type));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return questions;


    }

    public static List<MCQQuestion> getMCQQuestion(int id) throws SQLException {
        Connection connection = connectToDb();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mcq_questions WHERE question_id = ?");
        preparedStatement.setInt(1, id);
        List<MCQQuestion> mcq_questions = getAnswersResults(preparedStatement);
        connection.close();
        return mcq_questions;
    }


    private static List<MCQQuestion> getAnswersResults(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        int question_id;
        String answers;
        String[] options;

        List<MCQQuestion> mcq_questions = new ArrayList<>();

        while (resultSet.next()) {
            try {
                question_id = resultSet.getInt("question_id");
                answers = resultSet.getString("answers");
                String option = resultSet.getString("options");
                String cleaned = removeFirstandLast(option);
                options = cleaned.split(",");
                mcq_questions.add(new MCQQuestion(question_id, answers, options));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mcq_questions;


    }


    public static List<OpenQuestion> getOpenQuestion(int id) throws SQLException {
        Connection connection = connectToDb();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM open_questions WHERE question_id = ?");
        preparedStatement.setInt(1, id);
        List<OpenQuestion> open_questions = getOpenQuestionsResult(preparedStatement);
        connection.close();
        return open_questions;
    }


    private static List<OpenQuestion> getOpenQuestionsResult(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        int question_id;
        String answers;
        List<OpenQuestion> mcq_questions = new ArrayList<>();
        while (resultSet.next()) {
            try {
                question_id = resultSet.getInt("question_id");
                answers = resultSet.getString("answers");
                mcq_questions.add(new OpenQuestion(question_id, answers));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mcq_questions;


    }


    // Function to remove the first and
    // the last character of a string
    public static String removeFirstandLast(String str) {
        // Creating a StringBuffer object
        StringBuilder sb = new StringBuilder(str);
        // Removing the last character
        // of a string
        sb.delete(str.length() - 1, str.length());
        // Removing the first character
        // of a string
        sb.delete(0, 1);
        // Converting StringBuffer into
        // string & return modified string
        return sb.toString();
    }

}
