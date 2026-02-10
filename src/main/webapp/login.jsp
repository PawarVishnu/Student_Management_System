<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Login</title>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f2f2f2;
}
.box {
    width: 320px;
    margin: 120px auto;
    background: white;
    padding: 25px;
    border-radius: 6px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
}
input, button {
    width: 100%;
    padding: 10px;
    margin: 8px 0;
}
button {
    background-color: #007bff;
    border: none;
    color: white;
    cursor: pointer;
}
button:hover {
    background-color: #0056b3;
}
.error {
    color: red;
    text-align: center;
}
a {
    text-decoration: none;
}
</style>

</head>
<body>

<div class="box">
    <h3 style="text-align:center;">User Login</h3>

    <!-- ❌ Error Message -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p class="error"><%= error %></p>
    <%
        }
    %>

    <form action="login" method="post">

        Email:
        <input type="email" name="email" required />

        Password:
        <input type="password" name="password" required />

        <button type="submit">Login</button>
    </form>

    <p style="text-align:center; margin-top:10px;">
        New user?
        <a href="register.jsp">Register here</a>
    </p>
</div>

</body>
</html>
