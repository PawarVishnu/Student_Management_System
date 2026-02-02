package com.sms.service;

import java.util.List;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

public class StudentService {

    private StudentDAO dao;

    public StudentService() {
        dao = new StudentDAO();
    }

 // 🔹 ADD STUDENT
    public String addStudent(Student s) {

        boolean status = false;
        try {
            status = dao.addStudent(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (status) {
            return "Student successfully add झाला";
        } else {
            return "Student add failed";
        }
    }

    // 🔹 VIEW ALL STUDENTS
    public List<Student> getAllStudents() {
        List<Student> list = null;
        try {
            list = dao.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 🔹 GET STUDENT BY ID (FOR UPDATE)
    public Student getStudentById(int id) {
        Student s = null;
        try {
            s = dao.getStudentById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public boolean updateStudent(Student s) {
        try {
            dao.updateStudent(s);   // ✅ void method call
            return true;            // update successful
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 🔹 DELETE STUDENT
 
    public boolean deleteStudent(int id) {
        try {
            dao.deleteStudent(id);   // ✅ void method call
            return true;             // delete successful
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
