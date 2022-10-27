<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@include file="template/header2.jsp" %>

<div id="content-right">
    <div class="path-admin">ORDERS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="order-title">
                <b>Filter by Order date:</b>
                <form>
                    From: <input type="date" name="txtStartOrderDate"/>
                    To: <input type="date" name="txtEndOrderDate"/>
                    <input type="submit" value="Filter">
                </form>
            </div>
            <div id="order-table">
                <table id="orders">
                    <tr>
                        <th>OrderID</th>
                        <th>OrderDate</th>
                        <th>RequiredDate</th>
                        <th>ShippedDate</th>
                        <th>Employee</th>
                        <th>Customer</th>
                        <th>Freight($)</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${order}" var="o">
                        <tr>
                            <td><a href="order-detail?id=${o.orderID}">#${o.orderID}</a></td>
                            <td>${o.orderDate}</td>
                            <td>${o.requiredDate}</td>
                            <td>${o.shippedDate}</td>
                            <c:forEach items="${employee}" var="e">
                                <c:if test="${o.employeeID==e.employeeID}">
                                    <td>${e.firstName}</td>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${customer}" var="c">
                                <c:if test="${o.customerID==c.customerID}">
                                    <td>${c.contactName}</td>
                                </c:if>
                            </c:forEach>
                            <td>${o.freight}</td>
                            <c:choose>
                                <c:when test="${o.shippedDate!=null}">
                                    <td style="color: green;">Completed</td>
                                </c:when>
                                <c:when test="${o.shippedDate==null}">
                                    <td style="color: blue;">Pending | <a href="#">Cancel</a></td>
                                </c:when>
                                <c:when test="${o.shippedDate > o.requiredDate}">
                                    <td style="color: red; font-weight: bold">Out of date</td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color: red;">Order canceled</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td><a href="#">#4</a></td>
                        <td>11-10-2022</td>
                        <td>12-10-2022</td>
                        <td>11-10-2022</td>
                        <td>Tom</td>
                        <td>Susue</td>
                        <td>300</td>
                        <td style="color: green;">Completed</td>
                    </tr>
                    <tr>
                        <td><a href="#">#3</a></td>
                        <td>11-10-2022</td>
                        <td>12-10-2022</td>
                        <td></td>
                        <td>Tom</td>
                        <td>John</td>
                        <td>1000</td>
                        <td style="color: blue;">Pending | <a href="#">Cancel</a></td>
                    </tr>
                    <tr>
                        <td><a href="#">#2</a></td>
                        <td>10-10-2022</td>
                        <td>12-10-2022</td>
                        <td></td>
                        <td>Marry</td>
                        <td>Ronaldo</td>
                        <td>1500</td>
                        <td style="color: red;">Order canceled</td>
                    </tr>
                    <tr>
                        <td><a href="#">#1</a></td>
                        <td>09-10-2022</td>
                        <td>10-10-2022</td>
                        <td>11-10-2022</td>
                        <td>Marry</td>
                        <td>David</td>
                        <td>200</td>
                        <td style="color: green;">Completed</td>
                    </tr>
                </table>
            </div>
            <div id="paging">
                <div class="pagination">
                    <a href="#">&laquo;</a>
                    <a href="#">1</a>
                    <a href="#" class="active">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">6</a>
                    <a href="#">&raquo;</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="template/footer2.jsp" %>