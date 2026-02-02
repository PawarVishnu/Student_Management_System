<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%@ page import="java.util.*, com.sms.model.Student" %>

<%
    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Students</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">


<style>
body {
    font-family: Arial;
    background-color: #f2f2f2;
}

.container {
    width: 95%;
    margin: 20px auto;
    background: white;
    padding: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    border: 1px solid #999;
    padding: 8px;
    text-align: center;
}

th {
    background-color: #e0e0e0;
}

a {
    text-decoration: none;
    margin: 0 5px;
}

.top-bar {
    display: flex;
    justify-content: space-between;
    margin-bottom: 15px;
}
</style>

</head>
<body>

<div class="container mt-4">

    <div class="top-bar">
        <h2>Student List</h2>
        <div class="d-flex justify-content-between">
        <h3>Student Management System</h3>
            <a href="addStudent.jsp">➕ Add Student</a> |
            <a href="logout" class="btn btn-danger">🚪 Logout</a>
           
            <form action="searchStudent" method="get" class="my-3">
   			 <input type="text" name="keyword" class="form-control"
     	      placeholder="Search by Name / Course / City">
			</form>
			<hr>
        </div>
    </div>

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
            <th>Action</th>
        </tr>

        <%
            List<Student> list =
                (List<Student>) request.getAttribute("students");

            if (list != null && !list.isEmpty()) {
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
            <td>
                <a href="updateStudent?id=<%= s.getStudentId() %>">✏ Edit</a>
                |
                <a href="deleteStudent?id=<%= s.getStudentId() %>"
                   onclick="return confirm('Are you sure you want to delete this student?');">
                   ❌ Delete
                </a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="11">No Students Found</td>
        </tr>
        <%
            }
        %>

    </table>

</div>

</body>
</html>
