package com.sms.model;

public class Student {

    // ====== Basic Details ======
    private int studentId;
    private String name;
    private int age;
    private String gender;

    // ====== Contact Details ======
    private String mobile;
    private String email;
    private String city;

    // ====== Course & Fees ======
    private String courseName;
    private double totalFees;
    private double paidFees;

    // ====== Status Tracking ======
    private String status;     // Active / Inactive
    private String lifeCycle;  // INQUIRY / ACTIVE / COMPLETED

    // ====== Constructors ======

    // Default constructor (Servlet/JSP साठी गरजेचा)
    public Student() {
    }

    // Parameterized constructor (object तयार करताना वापरतो)
    public Student(int studentId, String name, int age, String gender,
                   String mobile, String email, String city,
                   String courseName, double totalFees, double paidFees,
                   String status, String lifeCycle) {

        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.city = city;
        this.courseName = courseName;
        this.totalFees = totalFees;
        this.paidFees = paidFees;
        this.status = status;
        this.lifeCycle = lifeCycle;
    }

    // ====== Getters & Setters ======

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(double totalFees) {
        this.totalFees = totalFees;
    }

    public double getPaidFees() {
        return paidFees;
    }

    public void setPaidFees(double paidFees) {
        this.paidFees = paidFees;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    // ====== Helper Method ======
    public double getPendingFees() {
        return totalFees - paidFees;
    }
}
