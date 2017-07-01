package com.controller;

import com.service.db.StudentFavoriteTeacherService;
import com.service.db.StudentService;
import com.service.db.TeacherInfoService;
import com.service.db.TeacherListByRating;
import com.service.db.helper.TeacherInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeacherController extends HttpServlet {
    private StudentFavoriteTeacherService favoriteTeacherService = new StudentFavoriteTeacherService();
    private TeacherInfoService teacherInfoService = new TeacherInfoService();
    private TeacherListByRating teacherListByRating = new TeacherListByRating();
    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer studentId = getStudentId(req.getParameter("studentId"));
        if (studentId == null || !studentService.studentExists(studentId))
            req.getRequestDispatcher("error-student.jsp").forward(req, resp);

        req.setAttribute("favouriteTeacher", getFavouriteTeacher(studentId));
        req.setAttribute("topRatedTeachers", teacherInfoService.getInfos(new ArrayList<>(teacherListByRating.getIdsOfTeachersThatHaveRankA())));

        req.getRequestDispatcher("teacher.jsp").forward(req, resp);
    }

    private TeacherInfo getFavouriteTeacher(Integer studentId) {
        Integer favouriteTeacherId = favoriteTeacherService.getFavoriteTeacher(studentId);
        return favouriteTeacherId == null ? null : teacherInfoService.getInfo(favouriteTeacherId);
    }

    private Integer getStudentId(String studentId) {
        if (studentId == null) return null;
        try {
            return Integer.valueOf(studentId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
