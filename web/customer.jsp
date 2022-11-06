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
    <div class="path-admin">CUSTOMERS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="order-title">
                

            </div>

            <div id="order-table">
                <table id="orders">
                    <tr>
                        <th>CustomerID</th>
                        <th>CompanyName</th>
                        <th>ContactName</th>
                        <th>ContactTitle</th>
                        <th>Address</th>

                    </tr>
                    <c:forEach items="${customer}" var="c">
                        <tr>
                            <td>${c.getCustomerID()}</td>
                            <td>${c.getCompanyName()}</td>
                            <td>${c.getContactName()}</td>
                            <td>${c.getContactTitle()}</td>
                            <td>${c.getAddress()}</td>
                        </tr>

                    </c:forEach>
                </table>
            </div>
            <div id="paging">
                <div class="pagination">
                    <a href="#">&laquo;</a>
                    <a href="#" class="active">1</a>
<!--                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">6</a>-->
                    <a href="#">&raquo;</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="template/footer2.jsp" %>
