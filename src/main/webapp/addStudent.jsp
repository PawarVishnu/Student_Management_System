<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>

<style>
    body {
        font-family: Arial;
        background-color: #f2f2f2;
    }
    .container {
        width: 500px;
        margin: 50px auto;
        background: white;
        padding: 20px;
        border-radius: 8px;
    }
    input, select {
        width: 100%;
        padding: 8px;
        margin: 6px 0;
    }
    button {
        width: 100%;
        padding: 10px;
        background-color: green;
        color: white;
        border: none;
    }
    .msg {
        margin-top: 10px;
        color: blue;
        font-weight: bold;
    }
</style>

</head>
<body>

<div class="container">
    <h2>Student Add Form</h2>

    <form action="addStudent" method="post">

        Student ID:
        <input type="number" name="studentId" required />

        Name:
        <input type="text" name="name" required />

        Age:
        <input type="number" name="age" required />

        Gender:
        <select name="gender">
            <option>Male</option>
            <option>Female</option>
        </select>

        Mobile:
        <input type="text" name="mobile" />

        Email:
        <input type="email" name="email" />

        City:
        <input type="text" name="city" />

        Course Name:
        <input type="text" name="courseName" />

        Total Fees:
        <input type="number" name="totalFees" />

        Paid Fees:
        <input type="number" name="paidFees" />

        <button type="submit">Add Student</button>
    </form>

    <!-- Message from Servlet -->
    <div class="msg">
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
                out.println(msg);
            }
        %>
    </div>
</div>

</body>
</html>
