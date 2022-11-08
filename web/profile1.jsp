<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="template/header.jsp" %>

<%@include file="template/profile_left.jsp" %>

<div id="content-right">
    <div class="path">LIST ORDERS</b></div>
    <div class="content-main">

        <div id="profile-content-order">

            <div> 
                <c:forEach items="${orders}" var="o">
                    <div class="profile-order-title">
                        <div class="profile-order-title-left">
                            <div>Order creation date: ${o.getOrderDate()}</div>
                            <div>Order: <a href="#">#${o.getOrderID()}</a></div>

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
                    int OrderID = ((Orders)pageContext.findAttribute("o")).getOrderID();
                    ArrayList<OrderDetail> ods = new OrderDetailDAO().getOrderDetailsByOrderID(OrderID);
                    request.setAttribute("ods", ods);
                    %>

                    <c:forEach items="${ods}" var="od">
                        <div class="order-detail">
                            <div class="profile-order-content">
                                <div class="profile-order-content-col1">
                                    <a href="detail?pid=${od.getProductID()}"><img src="img/2.jpg" width="100%"/></a>
                                </div>
                                <div class="profile-order-content-col2">${od.getProductName()}</div>
                                <div class="profile-order-content-col3">Quantity: ${od.getQuantity()}</div>
                                <div class="profile-order-content-col4">${od.getAmmount()} $</div>
                            </div>
                        </div>
                    </c:forEach>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</div>

<%@include file="template/footer.jsp" %>

<script type="text/javascript" src="lib/jquery/dist/jquery.min.js"></script>
<script>
                                    function show(o) {
                                        console.log(o)
                                        if (o.nextElementSibling.style.display === "flex") {
                                            o.nextElementSibling.style.display = "none";
                                        } else {
                                            o.nextElementSibling.style.display = "flex";
                                        }
                                    }

//                            $(document).ready(function () {
//                                $(".order-detail").hide();
//                                $(".order-id").click(function () {
//                                    // $(".order-detail").show();
//                                    // $(".order-detail").fadeIn(1000);
//                                    $(".order-detail").toggle(1000);
//                                });
//                            });

                                    function warnCancel() {
                                        if (confirm("Cancel order will not be reverted! Do you want to continue?")) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }
</script>

