<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/30/2023
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>create</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<a style="margin-left: 100px; margin-top: 100px" class="btn btn-info" href="students">Back</a>
<div class="container">
    <h1 style="text-align: center">Add new Student</h1>
    <form style="width: 500px; margin: auto"
          action="/students?action=create" method="post">
        <div class="mb-3">
            <label class="form-label">Name</label><br>
            <input type="text" name="name" placeholder="Enter name">
        </div>
        <div class="mb-3">
            <label class="form-label">Email Address</label><br>
            <input type="text" name="email" placeholder="Enter email">
        </div>
        <div class="mb-3">
            <label class="form-label">Date of Birth</label><br>
            <input type="date" name="dob" placeholder="Enter Date of Birth">
        </div>
        <div class="mb-3">
            <label class="form-label">Address</label><br>
            <input type="text" name="address" placeholder="Enter Address">
        </div>
        <div class="mb-3">
            <label class="form-label">Phone number</label> <br>
            <input type="text" name="phone" placeholder="Enter phone number">
        </div>

        <div class="mb-3">
            <label class="form-label">Class</label>
            <select class="form-select" name="classroom">
                <c:forEach items="${classroom}" var="c">
                    <option value="${c.getId()}"><c:out value="${c.getName()}"/></option>
                </c:forEach>
            </select>
        </div>
        <button class="btn btn-primary" type="submit">Create</button>
    </form>
</div>
</body>
</html>
