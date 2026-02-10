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

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String mobile = req.getParameter("mobile");
            String course = req.getParameter("course");

            UserDAO userDAO = new UserDAO();

            /* =========================
               🔹 DUPLICATE EMAIL CHECK
               ========================= */
            if (userDAO.isEmailExists(email)) {
                req.setAttribute("error", "Email already exists!");
                req.getRequestDispatcher("addUser.jsp").forward(req, res);
                return;
            }

            /* =========================
               🔹 PASSWORD ENCRYPTION
               ========================= */
            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

            /* =========================
               🔹 USER OBJECT
               ========================= */
            User u = new User();
            u.setName(name);
            u.setEmail(email);
            u.setPassword(encryptedPassword);
            u.setMobile(mobile);
            u.setCourse(course);
            u.setStatus("Active");

            /* =========================
               🔹 INSERT INTO DB
               ========================= */
            userDAO.addUser(u);

            res.sendRedirect("viewStudents");

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("addUser.jsp");
        }
    }
}
