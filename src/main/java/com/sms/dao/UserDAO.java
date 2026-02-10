package com.sms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sms.model.User;
import com.sms.util.DBUtil;

public class UserDAO {

    // 🔹 REGISTER / INSERT USER
    public boolean registerUser(User u) {

        boolean status = false;

        String sql =
          "INSERT INTO USERS(NAME, EMAIL, PASSWORD, MOBILE, COURSE, STATUS) " +
          "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword()); // 🔐 already encrypted
            ps.setString(4, u.getMobile());
            ps.setString(5, u.getCourse());
            ps.setString(6, u.getStatus());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // 🔹 READ ALL USERS
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        String sql =
          "SELECT USER_ID, NAME, EMAIL, MOBILE, COURSE, STATUS FROM USERS";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("USER_ID"));
                u.setName(rs.getString("NAME"));
                u.setEmail(rs.getString("EMAIL"));
                u.setMobile(rs.getString("MOBILE"));
                u.setCourse(rs.getString("COURSE"));
                u.setStatus(rs.getString("STATUS"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔹 GET USER BY ID
    public User getUserById(int id) {

        User u = null;
        String sql = "SELECT * FROM USERS WHERE USER_ID=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getInt("USER_ID"));
                u.setName(rs.getString("NAME"));
                u.setEmail(rs.getString("EMAIL"));
                u.setMobile(rs.getString("MOBILE"));
                u.setCourse(rs.getString("COURSE"));
                u.setStatus(rs.getString("STATUS"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    // 🔹 UPDATE USER
    public boolean updateUser(User u) {

        boolean status = false;

        String sql =
          "UPDATE USERS SET NAME=?, EMAIL=?, MOBILE=?, COURSE=?, STATUS=? " +
          "WHERE USER_ID=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getMobile());
            ps.setString(4, u.getCourse());
            ps.setString(5, u.getStatus());
            ps.setInt(6, u.getUserId());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // 🔹 DELETE USER
    public boolean deleteUser(int id) {

        boolean status = false;
        String sql = "DELETE FROM USERS WHERE USER_ID=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // 🔹 CHECK DUPLICATE EMAIL
    public boolean isEmailExists(String email) {

        boolean exists = false;
        String sql = "SELECT COUNT(*) FROM USERS WHERE EMAIL=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    // 🔹 INSERT
    public void addUser(User u) throws Exception {
        String sql =
          "INSERT INTO USERS(NAME, EMAIL, PASSWORD, MOBILE, COURSE, STATUS) " +
          "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getMobile());
            ps.setString(5, u.getCourse());
            ps.setString(6, u.getStatus());

            ps.executeUpdate();
        }
    }
}
