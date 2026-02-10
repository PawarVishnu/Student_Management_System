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

        try (Connection con = DBUtil.getConnection()) {

            String sql = "INSERT INTO student VALUES (?,?,?,?,?,?,?,?,?,?)";
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

        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT * FROM student";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapStudent(rs));
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

        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT * FROM student WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                s = mapStudent(rs);
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

        try (Connection con = DBUtil.getConnection()) {

            String sql = "UPDATE student SET name=?, age=?, gender=?, mobile=?, email=?, city=?, course_name=?, total_fees=?, paid_fees=? WHERE student_id=?";
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

        try (Connection con = DBUtil.getConnection()) {

            String sql = "DELETE FROM student WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* =========================
       SEARCH STUDENT
       ========================= */
    public List<Student> searchStudent(String keyword) {

        List<Student> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT * FROM student WHERE name LIKE ? OR course_name LIKE ? OR city LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapStudent(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    /* =========================
    PAGINATION - VIEW STUDENTS
    ========================= */
    public List<Student> getStudentsByPage(int page, int pageSize) {

        List<Student> list = new ArrayList<>();
        int start = (page - 1) * pageSize;

        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT * FROM student OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, start);
            ps.setInt(2, pageSize);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapStudent(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

 
    /* =========================
       DASHBOARD COUNTS
       ========================= */
    public int getTotalStudents() {
        return getCount("SELECT COUNT(*) FROM student");
    }

    public int getActiveStudents() {
        return getCount("SELECT COUNT(*) FROM student WHERE status='Active'");
    }

    public int getInactiveStudents() {
        return getCount("SELECT COUNT(*) FROM student WHERE status='Inactive'");
    }

    private int getCount(String sql) {

        int count = 0;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /* =========================
       COMMON MAPPER
       ========================= */
    private Student mapStudent(ResultSet rs) throws Exception {

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
        return s;
    }
}
