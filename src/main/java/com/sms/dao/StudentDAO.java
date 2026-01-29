package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sms.model.Student;
import com.sms.util.DBUtil;

public class StudentDAO {

    // ===============================
    // 1. ADD STUDENT
    // ===============================
    public boolean addStudent(Student s) {

        boolean status = false;

        try {
            Connection con = DBUtil.getConnection();

            String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, s.getStudentId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getGender());
            ps.setString(5, s.getMobile());
            ps.setString(6, s.getEmail());
            ps.setString(7, s.getCity());
            ps.setString(8, s.getCourseName());
            ps.setDouble(9, s.getTotalFees());
            ps.setDouble(10, s.getPaidFees());

            int i = ps.executeUpdate();
            if (i > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // ===============================
    // 2. VIEW ALL STUDENTS
    // ===============================
    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getConnection();
            String sql = "select * from student";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student();

                s.setStudentId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setAge(rs.getInt(3));
                s.setGender(rs.getString(4));
                s.setMobile(rs.getString(5));
                s.setEmail(rs.getString(6));
                s.setCity(rs.getString(7));
                s.setCourseName(rs.getString(8));
                s.setTotalFees(rs.getDouble(9));
                s.setPaidFees(rs.getDouble(10));

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ===============================
    // 3. GET STUDENT BY ID
    // ===============================
    public Student getStudentById(int id) {

        Student s = null;

        try {
            Connection con = DBUtil.getConnection();
            String sql = "select * from student where student_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                s = new Student();

                s.setStudentId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setAge(rs.getInt(3));
                s.setGender(rs.getString(4));
                s.setMobile(rs.getString(5));
                s.setEmail(rs.getString(6));
                s.setCity(rs.getString(7));
                s.setCourseName(rs.getString(8));
                s.setTotalFees(rs.getDouble(9));
                s.setPaidFees(rs.getDouble(10));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    // ===============================
    // 4. UPDATE STUDENT
    // ===============================
    public boolean updateStudent(Student s) {

        boolean status = false;

        try {
            Connection con = DBUtil.getConnection();

            String sql = "update student set name=?, age=?, gender=?, mobile=?, email=?, city=?, course_name=?, total_fees=?, paid_fees=? where student_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getGender());
            ps.setString(4, s.getMobile());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getCity());
            ps.setString(7, s.getCourseName());
            ps.setDouble(8, s.getTotalFees());
            ps.setDouble(9, s.getPaidFees());
            ps.setInt(10, s.getStudentId());

            int i = ps.executeUpdate();
            if (i > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // ===============================
    // 5. DELETE STUDENT
    // ===============================
    public boolean deleteStudent(int id) {

        boolean status = false;

        try {
            Connection con = DBUtil.getConnection();
            String sql = "delete from student where student_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int i = ps.executeUpdate();
            if (i > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // ===============================
    // 6. SEARCH STUDENT BY NAME
    // ===============================
    public List<Student> searchStudentByName(String name) {

        List<Student> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getConnection();
            String sql = "select * from student where name like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student();

                s.setStudentId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setAge(rs.getInt(3));
                s.setGender(rs.getString(4));
                s.setMobile(rs.getString(5));
                s.setEmail(rs.getString(6));
                s.setCity(rs.getString(7));
                s.setCourseName(rs.getString(8));
                s.setTotalFees(rs.getDouble(9));
                s.setPaidFees(rs.getDouble(10));

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
