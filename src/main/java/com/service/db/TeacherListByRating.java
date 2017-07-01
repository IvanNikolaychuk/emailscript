package com.service.db;

import com.service.db.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TeacherListByRating {
    public Set<Integer> getIdsOfTeachersThatHaveRankA() {
        try (Connection connection = ConnectionManager.newConnection()) {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT id AS rankAteacherID FROM teacherlist_houdai_rank WHERE rank='A'")
                    .executeQuery();

            return convertResultSetToIds(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    private Set<Integer> convertResultSetToIds(ResultSet resultSet) throws SQLException {
        Set<Integer> ids = new HashSet<>();

        while (resultSet.next()) {
            ids.add(resultSet.getInt("rankAteacherID"));
        }

        return ids;
    }
}
