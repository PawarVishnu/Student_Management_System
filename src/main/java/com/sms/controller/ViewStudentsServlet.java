package com.sms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sms.dao.StudentDAO;
import com.sms.dao.UserDAO;
import com.sms.util.DBUtil;
import com.sms.model.Student;

@WebServlet("/viewStudents")
public class ViewStudentsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔹 STUDENT PAGINATION (NO CHANGE)
        int page = 1;
        int recordsPerPage = 5;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        StudentDAO studentDAO = new StudentDAO();
        List<Student> students =
            studentDAO.getStudentsByPage(page, recordsPerPage);

        request.setAttribute("students", students);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages",
            (int)Math.ceil(studentDAO.getTotalStudents()*1.0/recordsPerPage));

        // 🔹 USER LIST
        try {
            UserDAO userDAO = new UserDAO();
            request.setAttribute("users", userDAO.getAllUsers());
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("viewStudents.jsp")
               .forward(request, response);
    }}
