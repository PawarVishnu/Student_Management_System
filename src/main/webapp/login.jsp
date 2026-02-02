<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<style>
body {
    font-family: Arial;
    background-color: #f2f2f2;
}
.box {
    width: 300px;
    margin: 100px auto;
    background: white;
    padding: 20px;
}
input, button {
    width: 100%;
    padding: 8px;
    margin: 5px 0;
}
</style>
</head>
<body>

<div class="box">
    <h3>Admin Login</h3>

    <form action="login" method="post">
        Username:
        <input type="text" name="username" required />
        Password:
        <input type="password" name="password" required />

        <button type="submit">Login</button>
    </form>

    <p style="color:red;">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </p>
</div>

</body>
</html>
