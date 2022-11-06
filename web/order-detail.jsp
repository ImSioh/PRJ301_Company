<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@include file="template/header2.jsp" %>

<div id="content-right">
    <div class="path-admin">ORDER DETAIL</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div>
                <div class="profile-order-title">

                    <%
                    Orders o = new OrderDAO().getOrderById(request.getParameter("id"));
                    request.setAttribute("o", o);
                    %>

                    <div class="profile-order-title-left">
                        <div>OrderID: ${param.id}</div>
                        <div>Order creation date: ${o.getOrderDate()}</div>
                    </div>
                    <div class="profile-order-title-right">
                        <c:if test="${o.getStatus() eq 'pending'}">
                            <a href="order-manage?action=cancel&cancelId=${o.getOrderID()}" onclick="return warnCancel()" style="text-decoration: none">
                                <span style="color: red;">Cancel</span>
                            </a>
                            <span style="color: blue;">Pending</span>
                        </c:if>
                        <c:if test="${o.getStatus() eq 'completed'}">
                            <span style="color: green;">Completed</span>
                        </c:if>
                    </div>
                </div>

                <%
            int OrderID = Integer.parseInt(request.getParameter("id"));
            ArrayList<OrderDetail> ods = new OrderDetailDAO().getOrderDetailsByOrderID(OrderID);
            request.setAttribute("ods", ods);
                %>


                <c:forEach items="${ods}" var="od">

                    <div class="profile-order-content">
                        <div class="profile-order-content-col1">
                            <a href="detail.jsp"><img src="img/2.jpg" width="100%"/></a>
                        </div>
                        <div class="profile-order-content-col2">${od.getProductName()}</div>
                        <div class="profile-order-content-col3">Quantity: ${od.getQuantity()}</div>
                        <div class="profile-order-content-col4">${od.getAmmount()} $</div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</div>

<%@include file="template/footer2.jsp" %>