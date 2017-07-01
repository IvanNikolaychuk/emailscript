package com.service.db;

import com.service.db.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public List<Integer> getAllStudentIds() {
        try (Connection connection = ConnectionManager.newConnection()) {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM apply_student_list")
                    .executeQuery();

            return convertResultSetToIds(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    public List<String> getAllEmails() {
        try (Connection connection = ConnectionManager.newConnection()) {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM apply_student_list")
                    .executeQuery();

            return convertResultSetToEmails(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    public String getEmail(int studentId) {
        try (Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM apply_student_list WHERE ID=?");
            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> emails = convertResultSetToEmails(resultSet);
            return emails.isEmpty() ? null : emails.get(0);
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    private List<String> convertResultSetToEmails(ResultSet resultSet) throws SQLException {
        List<String> emails = new ArrayList<>();
        while (resultSet.next()) {
            emails.add(resultSet.getString("student_email"));
        }

        return emails;
    }

    public boolean studentExists(int studentId) {
        try (Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM apply_student_list WHERE id=?");
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }


    private List<Integer> convertResultSetToIds(ResultSet resultSet) throws SQLException {
        List<Integer> ids = new ArrayList<>();

        while (resultSet.next()) {
            ids.add(resultSet.getInt("ID"));
        }

        return ids;
    }

}
