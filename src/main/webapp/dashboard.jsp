<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<title>Dashboard</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Chart.js -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<style>
body {
    background-color: #f2f2f2;
}
.card {
    border-radius: 10px;
}
.container-box {
    background: white;
    padding: 20px;
    border-radius: 10px;
}
</style>

</head>
<body>

<div class="container mt-4 container-box">

    <!-- 🔝 Top Bar -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h3>Student Management System - Dashboard</h3>
        <div>
            <a href="viewStudents" class="btn btn-primary btn-sm">📋 View Students</a>
            <a href="logout" class="btn btn-danger btn-sm">🚪 Logout</a>
        </div>
    </div>

    <!-- 📊 Cards -->
    <div class="row text-center mb-4">

        <div class="col-md-4">
            <div class="card bg-primary text-white">
                <div class="card-body">
                    <h5>Total Students</h5>
                    <h2>${total}</h2>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card bg-success text-white">
                <div class="card-body">
                    <h5>Active Students</h5>
                    <h2>${active}</h2>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card bg-danger text-white">
                <div class="card-body">
                    <h5>Inactive Students</h5>
                    <h2>${inactive}</h2>
                </div>
            </div>
        </div>

    </div>

    <!-- 📈 Chart Section -->
    <div class="row justify-content-center">
        <div class="col-md-6 text-center">
            <h5 class="mb-3">Students Status Chart</h5>
            <canvas id="studentChart"></canvas>
        </div>
    </div>

</div>

<!-- 📊 Chart Script -->
<script>
    const ctx = document.getElementById('studentChart');

    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['Active Students', 'Inactive Students'],
            datasets: [{
                data: [${active}, ${inactive}]
            }]
        }
    });
</script>

</body>
</html>
