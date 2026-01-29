<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.sms.model.Student" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Students</title>

<style>
table {
    width: 100%;
    border-collapse: collapse;
}
th, td {
    border: 1px solid black;
    padding: 8px;
}
th {
    background-color: #ddd;
}
</style>

</head>
<body>

<h2>Student List</h2>

<table>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Age</th>
    <th>Gender</th>
    <th>Mobile</th>
    <th>Email</th>
    <th>City</th>
    <th>Course</th>
    <th>Total Fees</th>
    <th>Paid Fees</th>
</tr>

<%
    List<Student> list =
        (List<Student>) request.getAttribute("students");

    if (list != null) {
        for (Student s : list) {
%>
<tr>
    <td><%= s.getStudentId() %></td>
    <td><%= s.getName() %></td>
    <td><%= s.getAge() %></td>
    <td><%= s.getGender() %></td>
    <td><%= s.getMobile() %></td>
    <td><%= s.getEmail() %></td>
    <td><%= s.getCity() %></td>
    <td><%= s.getCourseName() %></td>
    <td><%= s.getTotalFees() %></td>
    <td><%= s.getPaidFees() %></td>
</tr>
<%
        }
    }
%>

</table>

</body>
</html>
