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
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}">
                    From: <input type="date" name="txtStartOrderDate"/>
                    To: <input type="date" name="txtEndOrderDate"/>
                    <input type="submit" value="Filter">
                </form>

                <form>
                    <input type="text" name="txtSearch" placeholder="Enter order id to search"/>
                    <input type="submit" value="Search"/>
                </form>

                <div>
                    <c:if test="${not empty param.txtSearch}">   
                        #Keyword: ${param.txtSearch}
                    </c:if>
                    <c:if test="${not empty param.txtStartOrderDate}">   
                        #From: ${param.txtStartOrderDate}
                    </c:if>                            
                    <c:if test="${not empty param.txtEndOrderDate}">   
                        #To: ${param.txtEndOrderDate}
                    </c:if>

                </div>

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
                            <td>
                                <a href="order-detail.jsp?id=${o.orderID}">#${o.orderID}</a>

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
                                <c:when test="${o.shippedDate!=null}">
                                    <td style="color: green; font-weight: bold">Completed</td>
                                </c:when>
                                <c:when test="${o.shippedDate==null}">
                                    <td style="color: blue; font-weight: bold">Pending | <a href="#" style="color: red; font-weight: bold; text-decoration: none;">Cancel</a></td>
                                </c:when>
                                <c:when test="${o.shippedDate > o.requiredDate}">
                                    <td style="color: red; font-weight: bold">Out of date</td>
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



                    <c:url value = "order-list" var = "paging_url">
                        <c:param name = "txtSearch" value="${param.txtSearch}"/>
                        <c:param name = "txtStartOrderDate" value="${param.txtStartOrderDate}"/>
                        <c:param name = "txtEndOrderDate" value="${param.txtEndOrderDate}"/>
                    </c:url>

                    <c:if test="${numberOfPage > 1}">
                        <a href="${paging_url}&page=${page-1}">&laquo;</a>
                        <c:forEach var = "i" begin = "1" end = "${numberOfPage}">
                            <c:choose>
                                <c:when test="${i==page}">
                                    <a href="${paging_url}&page=${i}" class="active">${i}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${paging_url}&page=${i}">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <a href="${paging_url}&page=${page+1}">&raquo;</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="template/footer2.jsp" %>
