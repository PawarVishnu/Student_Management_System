package com.sms.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        StudentDAO dao = new StudentDAO();
        Student s = dao.getStudentById(id);

        request.setAttribute("student", s);
        request.getRequestDispatcher("updateStudent.jsp")
               .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Student s = new Student();
        s.setStudentId(Integer.parseInt(request.getParameter("studentId")));
        s.setName(request.getParameter("name"));
        s.setAge(Integer.parseInt(request.getParameter("age")));
        s.setGender(request.getParameter("gender"));
        s.setMobile(request.getParameter("mobile"));
        s.setEmail(request.getParameter("email"));
        s.setCity(request.getParameter("city"));
        s.setCourseName(request.getParameter("courseName"));
        s.setTotalFees(Double.parseDouble(request.getParameter("totalFees")));
        s.setPaidFees(Double.parseDouble(request.getParameter("paidFees")));

        StudentDAO dao = new StudentDAO();
        dao.updateStudent(s);

        response.sendRedirect("viewStudents");
    }
}
