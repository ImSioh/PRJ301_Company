<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@include file="template/header2.jsp" %>

<script>

    function warnCancel() {
        if (confirm("Cancel order will not be reverted! Do you want to continue?")) {
            return true;
        } else {
            return false;
        }
    }
</script>

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

                <form>
                    <input type="text" name="txtSearch" placeholder="Enter order id to search"/>
                    <input type="submit" value="Search"/>
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
                            <td><a href="order-detail.jsp?id=${o.orderID}">#${o.orderID}</a>
                                <form>
                                    <input type="submit" value="${o.orderID}">
                                </form>
                            </td>
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
                                <c:when test="${o.getStatus() eq 'completed'}">
                                    <td style="color: green;">Completed</td>
                                </c:when>
                                <c:when test="${o.getStatus() eq 'pending'}">
                                    <td style="color: blue;">Pending | <a href="order-list?action=cancel&id=${o.getOrderID()}" onclick="return warnCancel()">Cancel</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color: red; font-weight: bold">Order canceled</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>

                    </c:forEach>
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
