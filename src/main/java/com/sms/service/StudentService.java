package com.sms.service;

import java.util.List;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    // ====== Add Student with Validation ======
    public String addStudent(Student student) {

        // Validation
        if (student.getName() == null || student.getName().isEmpty()) {
            return "Student नाव रिकामं असू शकत नाही";
        }

        if (student.getAge() < 16) {
            return "Student वय 16 पेक्षा कमी असू शकत नाही";
        }

        if (student.getPaidFees() > student.getTotalFees()) {
            return "Paid fees total fees पेक्षा जास्त असू शकत नाही";
        }

        // Default values
        student.setStatus("Active");
        student.setLifeCycle("ACTIVE");

        studentDAO.addStudent(student);
        return "Student successfully add झाला";
    }

    // ====== Get All Students ======
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // ====== Get Student By ID ======
    public Student getStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    // ====== Delete Student ======
    public String deleteStudent(int studentId) {
        boolean result = studentDAO.deleteStudent(studentId);
        if (result) {
            return "Student Inactive केला";
        } else {
            return "Student सापडला नाही";
        }
    }
}
