<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.sms.model.Student, com.sms.model.User" %>

<%
    // 🔐 Session Security
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Students</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
body {
    background-color: #f2f2f2;
}
.container-box {
    background: white;
    padding: 20px;
    border-radius: 8px;
}
a {
    text-decoration: none;
}
</style>
</head>

<body>

<div class="container mt-4 container-box">

    <!-- 🔝 Top Bar -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Student Management System</h3>
        <div>
            <a href="addStudent.jsp" class="btn btn-success btn-sm">➕ Add Student</a>
            <a href="addUser.jsp" class="btn btn-primary btn-sm">➕ Add User</a>
            <a href="logout" class="btn btn-danger btn-sm">🚪 Logout</a>
        </div>
    </div>

    <!-- ================= STUDENT SECTION ================= -->

    <h4 class="mb-2">🎓 Students</h4>

    <!-- 🔍 Search -->
    <form action="searchStudent" method="get" class="mb-3">
        <input type="text" name="keyword" class="form-control"
               placeholder="Search by Name / Course / City">
    </form>

    <!-- 📋 Student Table -->
    <table class="table table-bordered table-striped text-center">
        <thead class="table-secondary">
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
        </thead>

        <tbody>
        <%
            List<Student> students =
                (List<Student>) request.getAttribute("students");

            if (students != null && !students.isEmpty()) {
                for (Student s : students) {
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
                <a href="updateStudent?id=<%= s.getStudentId() %>"
                   class="btn btn-warning btn-sm">✏ Edit</a>

                <a href="deleteStudent?id=<%= s.getStudentId() %>"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('Delete this student?');">
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
        </tbody>
    </table>

    <!-- 📄 Pagination -->
    <%
        Integer currentPage =
            (Integer) request.getAttribute("currentPage");
        Integer totalPages =
            (Integer) request.getAttribute("totalPages");

        if (currentPage != null && totalPages != null) {
    %>
    <nav>
        <ul class="pagination justify-content-center">

            <% if (currentPage > 1) { %>
            <li class="page-item">
                <a class="page-link"
                   href="viewStudents?page=<%= currentPage - 1 %>">
                   Previous
                </a>
            </li>
            <% } %>

            <%
                for (int i = 1; i <= totalPages; i++) {
            %>
            <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                <a class="page-link"
                   href="viewStudents?page=<%= i %>"><%= i %></a>
            </li>
            <%
                }
            %>

            <% if (currentPage < totalPages) { %>
            <li class="page-item">
                <a class="page-link"
                   href="viewStudents?page=<%= currentPage + 1 %>">
                   Next
                </a>
            </li>
            <% } %>

        </ul>
    </nav>
    <%
        }
    %>

    <hr class="my-4">

    <!-- ================= USER SECTION ================= -->

    <h4 class="mb-2">👤 Users</h4>

    <table class="table table-bordered table-striped text-center">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Course</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<User> users =
                (List<User>) request.getAttribute("users");

            if (users != null && !users.isEmpty()) {
                for (User u : users) {
        %>
        <tr>
            <td><%= u.getUserId() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getMobile() %></td>
            <td><%= u.getCourse() %></td>
            <td><%= u.getStatus() %></td>
            <td>
                <a href="editUser?id=<%= u.getUserId() %>"
                   class="btn btn-warning btn-sm">✏ Edit</a>

                <a href="deleteUser?id=<%= u.getUserId() %>"
                   class="btn btn-danger btn-sm"
                   onclick="return confirm('Delete this user?');">
                   ❌ Delete
                </a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="7">No Users Found</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

</div>

</body>
</html>
