<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="template/header.jsp" %>

<div id="content">
    <div id="content-left">
        <h3>CATEGORY</h3>
        <c:forEach items="${category}" var="c">
            <ul>
                <a href="category?id=${c.getCategoryID()}"><li>${c.getCategoryName()}</li></a>
            </ul>
        </c:forEach>
    </div>
    <div id="content-right">
        <!--path-->
        <c:forEach items="${category}" var="c">
            <c:forEach items="${product}" var="p" begin="0" end="0">
                <c:if test="${c.categoryID==p.categoryID}">
                    <div class="path">${c.getCategoryName()}</b></div>
                    </c:if>
                </c:forEach>
            </c:forEach>
        <div class="content-main">
            <c:forEach items="${product}" var="p" begin="0" end="3">
                <div class="product">
                    <a href="detail?pid=${p.productID}"><img src="img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="detail?pid=${p.productID}">${p.getProductName()}</a></div>
                    <div class="price">${p.getUnitPrice()}</div>
                    <div class="buy"><a href="">Buy now</a></div>
                </div>
            </c:forEach>
        </div>

        <div class="content-main">
            <c:forEach items="${product}" var="p" begin="4" end="7">
                <div class="product">
                    <a href="detail?pid=${p.productID}"><img src="img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="detail?pid=${p.productID}">${p.getProductName()}</a></div>
                    <div class="price">${p.getUnitPrice()}</div>
                    <div class="buy"><a href="">Buy now</a></div>
                </div>
            </c:forEach>
        </div>

        <div class="content-main">
            <c:forEach items="${product}" var="p" begin="8" end="11">
                <div class="product">
                    <a href="detail?pid=${p.productID}"><img src="img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="detail?pid=${p.productID}">${p.getProductName()}</a></div>
                    <div class="price">${p.getUnitPrice()}</div>
                    <div class="buy"><a href="">Buy now</a></div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp" %>