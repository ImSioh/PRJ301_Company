<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="content">
    <div id="content-left">
        <c:set value="${customer}" var="cus"></c:set>
        <h3 style="font-weight: normal;">Welcome, ${cus.contactName}</h3>
        <h3>Account Management</h3>
        <ul>
            <a href="<c:url value="/account/profile"/>"><li>Personal information</li></a>
        </ul>
        <h3>My order</h3>
        <ul>
            <a href="<c:url value="/order-manage?action=display&subset=all"/>"><li>All orders</li></a>
            <a href="<c:url value="/order-manage?action=display&subset=cancel"/>"><li>Canceled order</li></a>
        </ul>
    </div>