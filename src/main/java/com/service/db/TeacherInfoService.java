package com.service.db;

import com.service.db.connection.ConnectionManager;
import com.service.db.helper.TeacherInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.service.db.DateService.toReadableDates;
import static com.service.db.TeacherListScheduleService.DateUnit.DAY;
import static com.service.db.TeacherListScheduleService.DateUnit.TIME;

public class TeacherInfoService {
    private AvgTeacherScoreService avgTeacherScoreService = new AvgTeacherScoreService();
    private TeacherListScheduleService teacherListScheduleService = new TeacherListScheduleService();

    public TeacherInfo getInfo(int teacherId) {
        try (Connection connection = ConnectionManager.newConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM teacherlist_houdai WHERE id = ?");
            preparedStatement.setInt(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return convertResultSetToTeacherInfo(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Error when quering database: " + e.getMessage());
        }
    }

    public List<TeacherInfo> getInfos(List<Integer> teacherIds) {
        List<TeacherInfo> teacherInfos = new ArrayList<>();

        for (Integer teacherId : teacherIds) {
            teacherInfos.add(getInfo(teacherId));
        }

        return teacherInfos;
    }

    private TeacherInfo convertResultSetToTeacherInfo(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return null;
        final int teacherId = resultSet.getInt("ID");

        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setId(teacherId);
        teacherInfo.setName(resultSet.getString("name"));
        teacherInfo.setJapLevel(resultSet.getInt("japLevel"));
        teacherInfo.setJapJessLevel(resultSet.getInt("japJessLevel"));
        teacherInfo.setStudyMajor(resultSet.getString("studyMajor"));
        teacherInfo.setOccupation(resultSet.getString("occupation"));
        teacherInfo.setUniversity(resultSet.getString("university"));
        teacherInfo.setAvgScore(avgTeacherScoreService.getAvgScoreForTeacher(teacherId));
        List<String> curriculum = toReadableDates(
                teacherListScheduleService.getClassDates(teacherId, DAY),
                teacherListScheduleService.getClassDates(teacherId, TIME)
        );

        teacherInfo.setCurriculum(curriculum);
        return teacherInfo;
    }
}
