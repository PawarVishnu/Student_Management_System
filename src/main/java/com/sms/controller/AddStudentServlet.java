package com.sms.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sms.model.Student;
import com.sms.service.StudentService;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String courseName = request.getParameter("courseName");
        double totalFees = Double.parseDouble(request.getParameter("totalFees"));
        double paidFees = Double.parseDouble(request.getParameter("paidFees"));

        Student student = new Student();
        student.setStudentId(studentId);
        student.setName(name);
        student.setAge(age);
        student.setGender(gender);
        student.setMobile(mobile);
        student.setEmail(email);
        student.setCity(city);
        student.setCourseName(courseName);
        student.setTotalFees(totalFees);
        student.setPaidFees(paidFees);

        String message = studentService.addStudent(student);

        request.setAttribute("msg", message);
        request.getRequestDispatcher("addStudent.jsp").forward(request, response);
    }
}
