<%@ page import="com.sms.model.Student" %>

<%
    Student s = (Student) request.getAttribute("student");
%>

<h2>Update Student</h2>

<form action="updateStudent" method="post">

    <input type="hidden" name="studentId" value="<%= s.getStudentId() %>" />

    Name: <input type="text" name="name" value="<%= s.getName() %>" /><br>
    Age: <input type="number" name="age" value="<%= s.getAge() %>" /><br>
    Gender: <input type="text" name="gender" value="<%= s.getGender() %>" /><br>
    Mobile: <input type="text" name="mobile" value="<%= s.getMobile() %>" /><br>
    Email: <input type="email" name="email" value="<%= s.getEmail() %>" /><br>
    City: <input type="text" name="city" value="<%= s.getCity() %>" /><br>
    Course: <input type="text" name="courseName" value="<%= s.getCourseName() %>" /><br>
    Total Fees: <input type="number" name="totalFees" value="<%= s.getTotalFees() %>" /><br>
    Paid Fees: <input type="number" name="paidFees" value="<%= s.getPaidFees() %>" /><br>

    <button type="submit">Update</button>
</form>
