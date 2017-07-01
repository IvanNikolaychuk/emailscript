package com.service.db;

import com.service.db.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.service.db.TeacherListScheduleService.DateUnit.DAY;
import static com.service.db.TeacherListScheduleService.DateUnit.TIME;

public class TeacherListScheduleService {
    public enum DateUnit {
        DAY, TIME
    }

    public List<Long> getClassDates(int teacherId, DateUnit dateUnit) {
        try (Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM teacherlist_schedule_houdai_favoriteteacherid WHERE favoriteTeacherID = ?");
            preparedStatement.setInt(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return convertResultSetToDateIds(resultSet, dateUnit == TIME ? "timeid" : "dateid");
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    private List<Long> convertResultSetToDateIds(ResultSet resultSet, String columnLabel) throws SQLException {
        List<Long> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getLong(columnLabel));
        }

        return data;
    }

    public Set<Integer> getIdsOfTeachersThatHaveClassesThisWeek() {
        try (Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM teacherlist_schedule_houdai_favoriteteacherid " +
                            "WHERE (dateid>=? OR dateid<=?+7) AND (favoriteTeacherID = 1 OR favoriteTeacherID = 3);");
            final long currentDateId = DateService.getDayId();
            preparedStatement.setLong(1, currentDateId);
            preparedStatement.setLong(2, currentDateId);

            ResultSet resultSet = preparedStatement.executeQuery();

            return convertResultSetToIds(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    private Set<Integer> convertResultSetToIds(ResultSet resultSet) throws SQLException {
        Set<Integer> ids = new HashSet<>();

        while (resultSet.next()) {
            ids.add(resultSet.getInt("favoriteTeacherID"));
        }

        return ids;
    }
}
