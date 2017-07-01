package com.controller;

import com.service.db.StudentFavoriteTeacherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowStudentFavoriteController extends HttpServlet {
    private final StudentFavoriteTeacherService studentFavoriteService = new StudentFavoriteTeacherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("studentIdFavoriteTeacherMap", studentFavoriteService.getStudentIdFavoriteTeacherMap());
        req.getRequestDispatcher("student-favorite.jsp").forward(req, resp);
    }
}
