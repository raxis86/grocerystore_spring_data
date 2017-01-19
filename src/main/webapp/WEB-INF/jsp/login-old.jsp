<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User_model: raxis
  Date: 25.12.2016
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<ul id="menu">
    <li><a href="/Index">Главная</a></li>
</ul>
<body>
    <c:forEach items="${messages}" var="item">
        <div>${item}</div><br>
    </c:forEach>
    <H2>${message}</H2>

    <form action="/Login" method="post">
        Email:<input type="text" name="email"><br>
        Password:<input type="password" name="password"><br>
        <input type="submit" value="Войти">
    </form>
</body>
</html>
