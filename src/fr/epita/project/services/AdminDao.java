package fr.epita.project.services;

import fr.epita.project.datamodel.Admin;
import org.apache.ibatis.jdbc.ScriptRunner;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fr.epita.project.services.Utilities.connectToDb;

public class AdminDao {
    public static List<Admin> validateAdmin(String username, String password) throws SQLException {
        Connection connection = connectToDb();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Admin WHERE Admin.name = ? AND Admin.password = ?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        List<Admin> queried_admin = getQueryResults(preparedStatement);
        connection.close();
        return queried_admin;
    }


    private static List<Admin> getQueryResults(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        String name = null;
        int id;
        String password;

        List<Admin> admin = new ArrayList<>();
        while (resultSet.next()) {
            name = resultSet.getString("name");
            id = resultSet.getInt("id");
            password = resultSet.getString("password");
            admin.add(new Admin(name, id, password));
        }
        return admin;
    }

    public static void createQuestion(String question, String topics, String question_type, int difficulty) throws SQLException {
        Connection connection = connectToDb();
        //insert
        String insertQuery = "INSERT INTO Questions(question, topics, question_type, difficulty) values (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(insertQuery);
        ps.setString(1, question);
        ps.setString(2, "{" + topics + "}");
        ps.setString(3, question_type);
        ps.setInt(4, difficulty);
        ps.execute();
        connection.close();
    }


    public static void addMCQQuestion(String answers, String[] options) throws SQLException, FileNotFoundException {
        Connection connection = connectToDb();
        //get current id in table
        int count = AdminDao.getTotalQuestions();
//        insert
        String insertQuery = "INSERT INTO Mcq_questions(question_id, answers, options) values (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(insertQuery);
        ps.setInt(1, count + 1);
        ps.setString(2, answers);
        ps.setArray(3, connection.createArrayOf("text", options));
        ps.execute();
        connection.close();
    }

    public static int getTotalQuestions() throws SQLException, FileNotFoundException {
        Connection connection = connectToDb();
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(connection);
        //get current table count
        Reader getTotalCount = new BufferedReader(new FileReader("./resources/get_total_count.sql"));
        sr.runScript(getTotalCount);
        int count = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) FROM merged_questions");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            count = resultSet.getInt("count");

        }

        connection.close();
        return count;

    }

    public static void addOpenQuestion(String answers) throws SQLException, FileNotFoundException {
        Connection connection = connectToDb();
        //get current id in table
        int count = AdminDao.getTotalQuestions();
        //insert
        String insertQuery = "INSERT INTO Open_questions(question_id, answers) values (?, ?)";
        PreparedStatement ps = connection.prepareStatement(insertQuery);
        ps.setInt(1, count + 1);
        ps.setString(2, answers);
        ps.execute();
        connection.close();
    }

    public static void deleteAQuestion(int question_id) throws SQLException, FileNotFoundException {
        Connection connection = connectToDb();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM questions WHERE id = ? ");
        preparedStatement.setInt(1, question_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> data = new ArrayList<>();
        while (resultSet.next()) {
            String type = resultSet.getString("question_type");
            data.add(type);
            if (type.equals("mcq")) {
                try {
                    PreparedStatement prepared = connection.prepareStatement("DELETE FROM mcq_questions WHERE question_id = ?");
                    prepared.setInt(1, question_id);
                    prepared.executeUpdate();
                } catch (SQLException se) {
                    System.out.println(se.getErrorCode());
                } finally {
                    try {
                        finalDeletion(question_id, connection);
                    } catch (SQLException ex) {
                        System.out.println(ex.toString());
                    }
                }
            }

            if (type.equals("open_question")) {
                try {
                    PreparedStatement prepared = connection.prepareStatement("DELETE FROM open_questions WHERE question_id = ?");
                    prepared.setInt(1, question_id);
                    prepared.executeUpdate();
                } catch (SQLException se) {
                    System.out.println(se.getErrorCode());
                } finally {
                    try {
                        finalDeletion(question_id, connection);
                    } catch (SQLException ex) {
                        System.out.println(ex.toString());
                    }
                }
            }

        }

    }

    private static void finalDeletion(int question_id, Connection connection) throws SQLException {
        //delete from questions table
        PreparedStatement preparedStatementFinal = connection.prepareStatement("DELETE FROM questions WHERE id = ?");
        preparedStatementFinal.setInt(1, question_id);
        preparedStatementFinal.executeUpdate();
        connection.close();
    }

    public static void updateQuestionDifficulty(int question_id, int new_difficulty) throws SQLException, FileNotFoundException {
        Connection connection = connectToDb();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE questions SET difficulty = ? WHERE id = ? ");
        preparedStatement.setInt(1, new_difficulty);
        preparedStatement.setInt(2, question_id);
        preparedStatement.executeUpdate();
        connection.close();
    }
}
