package com.sms.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.sms.dao.UserDAO;
import com.sms.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔹 FORM DATA
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobile = request.getParameter("mobile");
        String course = request.getParameter("course");

        // default status
        String status = "Active";

        try {
            /* ============================
               🔍 DUPLICATE EMAIL CHECK
               ============================ */
            if (userDAO.isEmailExists(email)) {
                request.setAttribute("msg", "❌ Email already registered!");
                request.getRequestDispatcher("register.jsp")
                       .forward(request, response);
                return;
            }

            /* ============================
               🔐 PASSWORD ENCRYPTION
               ============================ */
            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

            /* ============================
               👤 USER OBJECT
               ============================ */
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(encryptedPassword);
            user.setMobile(mobile);
            user.setCourse(course);
            user.setStatus(status);

            /* ============================
               💾 SAVE TO DATABASE
               ============================ */
            boolean success = userDAO.registerUser(user);

            if (success) {
                // success → login page
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("msg", "❌ Registration failed!");
                request.getRequestDispatcher("register.jsp")
                       .forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "❌ Something went wrong!");
            request.getRequestDispatcher("register.jsp")
                   .forward(request, response);
        }
    }
}
