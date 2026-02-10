<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add User</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.container-box {
    background: white;
    padding: 20px;
    border-radius: 8px;
}
</style>
</head>
<body>

<div class="container mt-4 container-box">
    <h3 class="mb-3">➕ Add User</h3>

    <form action="addUser" method="post">

        <div class="mb-2">
            <label>Name</label>
            <input type="text" name="name" class="form-control" required>
        </div>

        <div class="mb-2">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required>
        </div>

        <div class="mb-2">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <div class="mb-2">
            <label>Mobile</label>
            <input type="text" name="mobile" class="form-control" required>
        </div>

        <div class="mb-2">
            <label>Course</label>
            <input type="text" name="course" class="form-control">
        </div>

        <div class="mb-3">
            <label>Status</label>
            <select name="status" class="form-control">
                <option value="Active">Active</option>
                <option value="Inactive">Inactive</option>
            </select>
        </div>

        <button class="btn btn-success">Save User</button>
        <a href="viewUsers" class="btn btn-secondary">Back</a>

    </form>

    <p class="text-danger mt-2">
        <%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>
    </p>

</div>
</body>
</html>
