package com.service.db;

import com.service.db.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvgTeacherScoreService {
    public double getAvgScoreForTeacher(int teacherId) {
        try (Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT avg(manzokudo) FROM teacher_evaluate WHERE teacherID = ?");
            preparedStatement.setInt(1, teacherId);

            ResultSet resultSet = preparedStatement.executeQuery();

            return convertResultSetToAvgScore(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    private double convertResultSetToAvgScore(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return 0;
        return resultSet.getDouble(1);
    }
}
