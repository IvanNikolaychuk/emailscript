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
import java.util.*;

import static java.util.Collections.singletonList;

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

        req.setAttribute("favouriteTeachers", getFavouriteTeachers(studentId));
        req.setAttribute("topRatedTeachers", getTopRatedTeachers(studentId));

        req.getRequestDispatcher("teacher.jsp").forward(req, resp);
    }

    private List<TeacherInfo> getTopRatedTeachers(Integer studentId) {
        Set<Integer> topRated = teacherListByRating.getIdsOfTeachersThatHaveRankA();
        topRated.removeAll(favoriteTeacherService.getFavoriteTeachers(studentId));

        return teacherInfoService.getInfos(new ArrayList<>(topRated));
    }

    private List<TeacherInfo> getFavouriteTeachers(Integer studentId) {
        List<Integer> favouriteTeacherId = favoriteTeacherService.getFavoriteTeachers(studentId);
        return favouriteTeacherId.isEmpty() ? new ArrayList<>() : teacherInfoService.getInfos(favouriteTeacherId);
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
