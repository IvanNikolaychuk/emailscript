package com.service.db;

import com.service.db.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentFavoriteTeacherService {
    public List<Integer> getFavoriteTeachers(int studentId) {
        try (Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM studentfavorite WHERE studentID=?");
            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            return convertResultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    private List<Integer> convertResultSetToList(ResultSet resultSet) throws SQLException {
        List<Integer> ids = new ArrayList<>();

        while (resultSet.next()) {
            ids.add(resultSet.getInt("favoriteTeacherID"));
        }

        return ids;
    }
}
