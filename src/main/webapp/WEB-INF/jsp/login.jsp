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
                            <p>Неверные логин, пароль или капча!</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div>
                            <p>You have been logged out successfully.</p>
                        </div>
                    </c:if>
                    <div>
                        <label for="email"></label>
                        <input type="text" id="email" name="username" placeholder="Enter Email" required>
                    </div>
                    <div>
                        <label for="password"></label>
                        <input type="password" id="password" name="password" placeholder="Enter Password" required>
                    </div>



                    <c:set var="rand1"><%= java.lang.Math.round(java.lang.Math.random() * 10) %>
                    </c:set>
                    <c:set var="rand2"><%= java.lang.Math.round(java.lang.Math.random() * 10) %>
                    </c:set>

                    <c:set var="rand1" value="${rand1}" scope="session"/>
                    <c:set var="rand2" value="${rand2}" scope="session"/>

                    <label>Сложите: ${rand1}+${rand2} </label>

                    <div class="">
                        <input name="captcha" type="text" placeholder="${rand1}+${rand2}?" required="true"/>
                    </div>



                    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />

                    <div>
                        <input type="submit" value="Войти">
                    </div>
                </form>
            </div>
</body>
</html>