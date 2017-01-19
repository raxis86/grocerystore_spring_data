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
    <title>Список заказов</title>
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

    <div>Ваши заказы:</div>
    <c:forEach items="${orderlist}" var="item">
    <Table>
      <th>Статус</th>
      <th>Дата</th>
      <th>Сумма</th>
      <th>Адрес доставки</th>
      <th></th>
      <tr>
        <td>${item.getStatus()}</td>
        <td>${item.getDate()}</td>
        <td>${item.getPrice()}</td>
        <td>${item.getAddress()}</td>
        <td>
            <form action="/OrderList" method="post">
                <input type="hidden" name="orderid" value="${item.getId()}">
                <input type="submit" value="Отменить заказ">
            </form>
        </td>
      </tr>
      <tr>
        <table>
            <th>Наименование</th>
            <th>Количество</th>
            <c:forEach items="${item.getGroceries()}" var="map">
                <tr>
                    <td>${map.key}</td>
                    <td>${map.value}</td>
                </tr>
                </br>
            </c:forEach>
        </table>
      </tr>
    </Table>
    <br>
    </c:forEach>


  </body>
</html>
