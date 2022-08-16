package fr.epita.project.services;

import fr.epita.project.datamodel.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static fr.epita.project.services.Utilities.connectToDb;

public class StudentsDao {

    public static List<Student> createStudent(String student) throws SQLException {
        Connection connection = connectToDb();

        //insert
        String insertQuery = "INSERT INTO Students(name) values (?)";
        PreparedStatement ps = connection.prepareStatement(insertQuery);
        ps.setString(1, student);
        ps.execute();

        //get recently created student
        List<Student> data = getStudent(student);
        connection.close();
        return data;
    }


    public static List<Student> getStudent(String student_name) throws SQLException {
        Connection connection = connectToDb();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Students WHERE Students.name = ?");
        preparedStatement.setString(1, student_name);
        List<Student> queried_student = getQueryResults(preparedStatement);
        connection.close();
        return queried_student;
    }

    private static List<Student> getQueryResults(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        String name = null;
        int id;

        List<Student> student = new ArrayList<>();
        while (resultSet.next()) {
            name = resultSet.getString("name");
            id = resultSet.getInt("id");
            student.add(new Student(name, id));
        }
        return student;
    }

}
