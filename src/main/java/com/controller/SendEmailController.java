package com.controller;

import com.service.email.EmailSenderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendEmailController extends HttpServlet {
    private final EmailSenderService emailSenderService = new EmailSenderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        emailSenderService.sendEmailToAllStudents();
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
