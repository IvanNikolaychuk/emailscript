package com.service.email;

import com.service.db.StudentService;
import org.apache.commons.mail.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class EmailSenderService {
    private final StudentService studentService = new StudentService();

    private static final String PASSWORD = "Nc,4\\dPF&GN^Uw9s";
    private static final String MAIL = "teacherStatisticEmail01072017@gmail.com";

    private void sendEmail(String receiver, String content) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setSSL(true);
            email.setAuthenticator(new DefaultAuthenticator(MAIL, PASSWORD));
            email.setFrom("school@gmail.com");
            email.setSubject("Hello from Ivan");
            email.setHtmlMsg(content);
            email.addTo(receiver);
            email.send();
        } catch (EmailException e) {
            System.err.println("Cannot send email to " + receiver + ". " + e.getMessage());
        }
    }

    public void sendEmailToAllStudents() {
        List<Integer> studentIds = studentService.getAllStudentIds();
        for (Integer studentId : studentIds) {
            String email = studentService.getEmail(studentId);
            if (email != null) {
                sendEmail(email, studentId);
            }
        }
    }

    private void sendEmail(String email, Integer studentId) {
        final String baseUrl = "http://localhost:8080/teachers?studentId=";
        try (InputStream in = new BufferedInputStream(new URL(baseUrl + studentId).openStream());
             ByteArrayOutputStream bout = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[512];
            int read;
            while ((read = in.read(buffer)) != -1) {
                bout.write(buffer, 0, read);
            }
            sendEmail(email, new String(bout.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException("Cannot send email: " + email + ". " + e.getMessage());
        }
    }


}
