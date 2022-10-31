<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ABC Company</title>
        <% String path = request.getContextPath(); %>
        <!--<link href="<%= path %>/css/style.css" rel="stylesheet"/>-->
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>" />
        <link rel="icon" href="<c:url value="/img/logo.png"/>" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <%
            if(request.getSession().getAttribute("accSession")==null) {
        %>
        <c:redirect url="error.jsp"></c:redirect>
        <%  } else { 
                Account acc = (Account)request.getSession().getAttribute("accSession");
                if(acc.getRole()==2){
        %>
        <c:redirect url="error.jsp"></c:redirect>
        <%  } else { %>
        <div id="container">
            <div id="header">
                <div id="logo-admin">
                    Ecommerce Admin
                </div>
                <div id="banner-admin">
                    <ul>
                        <c:set value="${sessionScope.accSession}" var="acc"/>
                        <c:if test="${acc!=null}">
                            <li>Profile (<a href="<%= path %>/account/profile">${acc.email}</a>)</li>
                            </c:if>
                        <li><a href="account/signin">SignOut</a></li>
                    </ul>
                </div>
            </div>
            <div id="content">
                <div id="content-left">
                    <ul>
                        <a href="dashboard.jsp"><li>Dashboard</li></a>
                        <a href="order-list"><li>Orders</li></a>
                        <a href="product-list"><li>Products</li></a>
                        <a href="#"><li>Customers</li></a>
                    </ul>
                </div>