<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="template/header.jsp" %>

<%
    if(request.getSession().getAttribute("AccSession")!=null) {
        Account acc = (Account)request.getSession().getAttribute("AccSession");
//        out.print("Welcome: " + acc.getEmail());
    }
%>
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
        <div class="path">Hot</b></div>
        <div class="content-main">
            <c:forEach items="${productHot}" var="pHot" begin="0" end="3">
                <div class="product">
                    <a href="detail?pid=${pHot.productID}"><img src="img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="detail?pid=${pHot.productID}">${pHot.getProductName()}</a></div>
                    <div class="price">${pHot.getUnitPrice()}</div>
                    <div class="buy"><a href="">Buy now</a></div>
                </div>
            </c:forEach>
        </div>

        <div class="path">Best Sale</b></div>
        <div class="content-main">
            <c:forEach items="${productBestSale}" var="pBest" begin="0" end="3">
                <div class="product">
                    <a href="detail?pid=${pBest.productID}"><img src="img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="detail?pid=${pBest.productID}">${pBest.getProductName()}</a></div>
                    <div class="price">${pBest.getUnitPrice()}</div>
                    <div class="buy"><a href="">Buy now</a></div>
                </div>
            </c:forEach>
        </div>

        <div class="path">New Product</b></div>
        <div class="content-main">
            <c:forEach items="${productNew}" var="pNew" begin="0" end="3">
                <div class="product">
                    <a href="detail?pid=${pNew.productID}"><img src="img/1.jpg" width="100%"/></a>
                    <div class="name"><a href="detail?pid=${pNew.productID}">${pNew.getProductName()}</a></div>
                    <div class="price">${pNew.getUnitPrice()}</div>
                    <div class="buy"><a href="">Buy now</a></div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<%@include file="template/footer.jsp" %>