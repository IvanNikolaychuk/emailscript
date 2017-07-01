package com.service.email.html;

import com.service.db.StudentFavoriteTeacherService;
import com.service.db.StudentService;

import java.util.List;

public class EmailGenerator {
    private final StudentService studentService = new StudentService();
    private final StudentFavoriteTeacherService studentFavoriteTeacherService = new StudentFavoriteTeacherService();

    public String generateForAllStudents() {
        List<Integer> studentIds = studentService.getAllStudentIds();

        for (Integer studentId : studentIds) {
            String report = generateReportFor(studentId);
        }
        return "";
    }

    private String generateReportFor(Integer studentId) {
        Integer favouriteTeacherId = studentFavoriteTeacherService.getFavoriteTeacher(studentId);
        if (favouriteTeacherId == null) {
            return "You don't have any favourite teacher";
        }

        return null;
    }
}
