<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User_model: raxis
  Date: 20.12.2016
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="req" value="${pageContext.request}" />
<html>
  <head>
    <title>Оформление заказа</title>
  </head>
  <body>
  <div class="menu">
      <security:authorize access="isAnonymous()">
          <div> <a href="/Login">Вход</a> </div>
          <div> <a href="/Signin">Регистрация</a> </div>
      </security:authorize>
      <security:authorize access="isAuthenticated()">
          <div> <a href="/Logout">Выход</a> </div>
      </security:authorize>
  </div>

  <div>
      <security:authorize access="isAnonymous()">
          <div>Добро пожаловать в наш магазин!</div>
      </security:authorize>
      <security:authorize access="isAuthenticated()">
          <div>Добро пожаловать в наш магазин, <sec:authentication property="principal.username" />!</div>
      </security:authorize>
      <nav>
          <ul id="menu">
              <li><a href="/Index">Главная</a></li>
              <security:authorize access="isAnonymous()">
                  <li><a href="/GroceryList">Каталог товаров</a></li>
              </security:authorize>
              <sec:authorize access="hasRole('ROLE_USER')">
                  <li><a href="/GroceryList">Каталог товаров</a></li>
                  <li><a href="/CartList">Корзина покупок</a></li>
                  <li><a href="/OrderList">Список заказов</a></li>
              </sec:authorize>
              <sec:authorize access="hasRole('ROLE_ADMIN')">
                  <li><a href="/OrderListAdmin">Список заказов (админ-режим)</a></li>
                  <li><a href="/GroceryListAdmin">Каталог товаров (админ-режим)</a></li>
              </sec:authorize>
          </ul>
      </nav>
  </div>


    <div>Вы заказываете следующие продукты:</div>
    <Table>
      <tr>
        <th>Наименование</th>
        <th>Цена</th>
        <th>Количество</th>
        <th></th>
      </tr>
      <c:forEach items="${cart.getMap()}" var="item">
        <tr>
          <td>${item.key.getName()}</td>
          <td>${item.key.getPrice()}</td>
          <td>${item.value}</td>
        </tr>
        </br>
      </c:forEach>
    </Table>
    <br>
    <div>Итого:${totalprice}</div>
    <br>
    <div>Ваши данные:</div>
    <form action="/OrderAdd" method="post">
      <input type="hidden" name="userid" value="${user.getId()}">
      Имя: <input type="text" name="name" value="${user.getName()}"> <br>
      Фамилия: <input type="text" name="lastname" value="${user.getLastname()}"> <br>
      Отчество: <input type="text" name="surname" value="${user.getSurname()}"> <br>
      Телефон: <input type="text" name="phone" value="${user.getPhone()}"> <br>
      Адрес доставки: <input type="textarea" name="address" value="${user.getAddress()}"> <br>
      <%--<input type="hidden" name="returnurl" value="${req.requestURI}">--%>
      <input type="submit" value="Оформить">
    </form>

  </body>
</html>
