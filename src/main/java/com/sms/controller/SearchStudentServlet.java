package com.sms.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

@WebServlet("/searchStudent")
public class SearchStudentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        StudentDAO dao = new StudentDAO();
        List<Student> list = dao.searchStudent(keyword);

        request.setAttribute("students", list);
        request.getRequestDispatcher("viewStudents.jsp")
               .forward(request, response);
    }
}
