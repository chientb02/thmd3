<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container" style="width: 30%">
    <form action="students?action=search" method="post">
        <input type="text" name="search" >
        <button>Search</button>
    </form>
    <h1 style="text-align: center">List Student</h1>
    <a style="margin-left: 1px" class="btn btn-primary" href="students?action=create">add</a>
    <table style="border-collapse: collapse; border: 1px" class="table table-hover">
        <tr style="margin-top: 500px" >
            <th>#</th>
            <th>Name</th>
            <th>DateOfBirth</th>
            <th>Email</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Classroom</th>
            <th colspan="2">Action</th>
        </tr>
        <c:forEach items="${students}" var="student" varStatus="stt">
            <tr>
                <td><c:out value="${stt.count}"/></td>
                <td><c:out value="${student.getName()}"/></td>
                <td><c:out value="${student.getDateOfBirth().toString()}"/></td>
                <td><c:out value="${student.getEmail()}"/></td>
                <td><c:out value="${student.getAddress()}"/></td>
                <td><c:out value="${student.getPhoneNumber()}"/></td>
                <td><c:out value="${student.getClassroom().getName()}"/></td>
                <td><a class="btn btn-warning" href="students?action=update&&id=${student.getId()}">Edit</a></td>
                <td><a class="btn btn-warning" href="students?action=delete&&id=${student.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
