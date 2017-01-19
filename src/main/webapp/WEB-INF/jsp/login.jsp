<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<ul id="menu">
    <li><a href="/Index">Главная</a></li>
</ul>
<body>
<div>
                <c:url var="loginUrl" value="/login" />
                <form action="${loginUrl}" method="post" class="form-horizontal">
                    <c:if test="${param.error != null}">
                        <div>
                            <p>Invalid email and password.</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div>
                            <p>You have been logged out successfully.</p>
                        </div>
                    </c:if>
                    <div>
                        <label for="email"></label>
                        <input type="text" id="email" name="email" placeholder="Enter Email" required>
                    </div>
                    <div>
                        <label for="password"></label>
                        <input type="password" id="password" name="password" placeholder="Enter Password" required>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />

                    <div>
                        <input type="submit" value="Войти">
                    </div>
                </form>
            </div>
</body>
</html>