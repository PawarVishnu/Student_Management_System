package com.sms.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.sms.service.StudentService;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private StudentService service = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔐 Session Check
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 📊 Dashboard Data
        int total = service.getTotalStudents();
        int active = service.getActiveStudents();
        int inactive = service.getInactiveStudents();

        // 📦 Set Attributes
        request.setAttribute("total", total);
        request.setAttribute("active", active);
        request.setAttribute("inactive", inactive);

        // ➡ Forward to Dashboard JSP
        request.getRequestDispatcher("dashboard.jsp")
               .forward(request, response);
    }
}
