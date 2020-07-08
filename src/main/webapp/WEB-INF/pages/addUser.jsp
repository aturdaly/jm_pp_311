<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add New User:</title>
</head>
<body>

<form action="/admin/addUser" method="post">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password"/></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="number" name="age"/></td>
        </tr>
        <tr>
            <td>Role</td>
            <c:forEach items="${roleList}" var="role">
                <td>
                    <input type="checkbox" name="posRoles" value="${role.role}"/>${role.role}
                </td>
            </c:forEach>
        </tr>
    </table>
    <input type="submit" value="Add"/>
</form>

</body>
</html>
