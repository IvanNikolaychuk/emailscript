package com.service.db;

import com.service.db.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StudentFavoriteTeacherService {
    public Map<Integer, Integer> getStudentIdFavoriteTeacherMap() {
        try (Connection connection = ConnectionManager.newConnection()) {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM studentfavorite")
                    .executeQuery();

            return convertResultSetToMap(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    public Integer getFavoriteTeacher(int studentId) {
        try (Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM studentfavorite WHERE studentID=?");
            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt("favoriteTeacherID") : null;
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    private Map<Integer, Integer> convertResultSetToMap(ResultSet resultSet) throws SQLException {
        Map<Integer, Integer> studentIdFavoriteTeacherMap = new HashMap<>();

        while (resultSet.next()) {
            final int studentId = resultSet.getInt("studentId");
            final int favouriteTeacherId = resultSet.getInt("favoriteTeacherID");
            studentIdFavoriteTeacherMap.put(studentId, favouriteTeacherId);
        }

        return studentIdFavoriteTeacherMap;
    }
}
