package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sms.model.Student;
import com.sms.util.DBUtil;

public class StudentDAO {

    /* =========================
       ADD STUDENT
       ========================= */
	public boolean addStudent(Student s) {

	    int rows = 0;

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

	        rows = ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return rows > 0;
	}

    /* =========================
       VIEW ALL STUDENTS
       ========================= */
    public List<Student> getAllStudents() {

        List<Student> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getConnection();
            String sql = "select * from student";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student();

                s.setStudentId(rs.getInt("student_id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setGender(rs.getString("gender"));
                s.setMobile(rs.getString("mobile"));
                s.setEmail(rs.getString("email"));
                s.setCity(rs.getString("city"));
                s.setCourseName(rs.getString("course_name"));
                s.setTotalFees(rs.getDouble("total_fees"));
                s.setPaidFees(rs.getDouble("paid_fees"));

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /* =========================
       GET STUDENT BY ID
       ========================= */
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
                s.setStudentId(rs.getInt("student_id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setGender(rs.getString("gender"));
                s.setMobile(rs.getString("mobile"));
                s.setEmail(rs.getString("email"));
                s.setCity(rs.getString("city"));
                s.setCourseName(rs.getString("course_name"));
                s.setTotalFees(rs.getDouble("total_fees"));
                s.setPaidFees(rs.getDouble("paid_fees"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /* =========================
       UPDATE STUDENT
       ========================= */
    public void updateStudent(Student s) {

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

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* =========================
       DELETE STUDENT
       ========================= */
    public void deleteStudent(int id) {

        try {
            Connection con = DBUtil.getConnection();
            String sql = "delete from student where student_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Student> searchStudent(String keyword) {

        List<Student> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getConnection();
            String sql = "select * from student where name like ? or course_name like ? or city like ?";
            PreparedStatement ps = con.prepareStatement(sql);

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getInt("student_id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setGender(rs.getString("gender"));
                s.setMobile(rs.getString("mobile"));
                s.setEmail(rs.getString("email"));
                s.setCity(rs.getString("city"));
                s.setCourseName(rs.getString("course_name"));
                s.setTotalFees(rs.getDouble("total_fees"));
                s.setPaidFees(rs.getDouble("paid_fees"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
