<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    <title>Регистрация</title>
</head>
<ul id="menu">
    <li><a href="/Index">Главная</a></li>
</ul>
<body>
    <c:forEach items="${messages}" var="item">
        <div>${item}</div><br>
    </c:forEach>
    <H2>${message}</H2>
    <form action="/Signin" method="post">
        Email:<input type="text" name="email" value=""><br>
        Password:<input type="password" name="password" value=""><br>
        Имя:<input type="text" name="name" value="${name}"><br>
        Фамилия:<input type="text" name="lastname" value="${lastname}"><br>
        Отчество:<input type="text" name="surname" value="${surname}"><br>
        Адрес:<input type="textarea" name="address" value="${address}"><br>
        Телефон:<input type="text" name="phone" value="${phone}"><br>
        <input type="submit" value="Зарегистрироваться">
    </form>
</body>
</html>
