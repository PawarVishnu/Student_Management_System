package com.sms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.sms.util.DBUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // 👉 Open login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    // 👉 Handle login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String dbPassword = null;
        String userName = null;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT NAME, PASSWORD FROM USERS WHERE EMAIL = ? AND STATUS = 'Active'")) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userName = rs.getString("NAME");
                    dbPassword = rs.getString("PASSWORD");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Database Error! Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // 🔐 BCrypt Password Match
        if (dbPassword != null && BCrypt.checkpw(password, dbPassword)) {

            HttpSession session = request.getSession(true);
            session.setAttribute("user", userName);
            session.setAttribute("email", email);

            // 👉 Redirect to Dashboard (index.jsp)
            response.sendRedirect("index.jsp");

        } else {

            request.setAttribute("error", "Invalid Email or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
