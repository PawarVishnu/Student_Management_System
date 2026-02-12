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
<title>Dashboard</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="css/style.css">

</head>
<body>

<div class="wrapper">

    <!-- Sidebar -->
    <div class="sidebar">
        <div class="logo">
            <img src="images/logo.png" width="60">
        </div>

        <ul>
            <li><a href="index.jsp"><i class="fa fa-home"></i> Dashboard</a></li>
            <li><a href="viewStudents"><i class="fa fa-user-graduate"></i> Students</a></li>
            <li><a href="viewUsers"><i class="fa fa-users"></i> Users</a></li>
            <li><a href="#"><i class="fa fa-chart-bar"></i> Reports</a></li>
            <li><a href="logout"><i class="fa fa-sign-out-alt"></i> Logout</a></li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="main-content">

        <h3>Welcome, <%= session.getAttribute("user") %> 👋</h3>

        <!-- Stats Cards -->
        <div class="row mt-4">

            <div class="col-md-3">
                <div class="card-box bg-primary">
                    <i class="fa fa-school fa-2x"></i>
                    <h4>Schools</h4>
                    <p>69</p>
                </div>
            </div>

            <div class="col-md-3">
                <div class="card-box bg-success">
                    <i class="fa fa-chalkboard-teacher fa-2x"></i>
                    <h4>Teachers</h4>
                    <p>88</p>
                </div>
            </div>

            <div class="col-md-3">
                <div class="card-box bg-warning">
                    <i class="fa fa-user-graduate fa-2x"></i>
                    <h4>Students</h4>
                    <p>90</p>
                </div>
            </div>

            <div class="col-md-3">
                <div class="card-box bg-danger">
                    <i class="fa fa-users fa-2x"></i>
                    <h4>Parents</h4>
                    <p>128</p>
                </div>
            </div>

        </div>

    </div>
</div>

<script src="js/script.js"></script>

</body>
</html>
