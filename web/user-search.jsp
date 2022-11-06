<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="template/header.jsp" %>


<style>
    .card-layout {
        display: flex;

        /* Put a card in the next row when previous cards take all width */
        flex-wrap: wrap;

        margin-left: -0.25rem;
        margin-right: -0.25rem;
    }

    .card-layout__item {
        /* There will be 4 cards per row */
        flex-basis: 22%;

        padding-left: 0.25rem;
        padding-right: 0.25rem;
    }
</style>

<div id="content">
    <div id="content-left">
        <h3>Filter The Result</h3>
        <%--<c:forEach items="${category}" var="c">--%>
        <!--            <ul>
                        <a href="category?id=${c.getCategoryID()}"><li>${c.getCategoryName()}</li></a>
                    </ul>-->
        <%--</c:forEach>--%>
        <form>
            <input type="hidden" name="keyword" value="${param.keyword}">
            <select name="ddlCategory">
                <option disabled selected value> -- select an option -- </option>
                <c:forEach items="${category}" var="c">
                    <c:if test="${c.getCategoryID()==param.ddlCategory}">
                        <option value="${c.getCategoryID()}" selected>${c.getCategoryName()}</option>
                    </c:if>
                    <c:if test="${c.getCategoryID()!=param.ddlCategory}">
                        <option value="${c.getCategoryID()}" >${c.getCategoryName()}</option>
                    </c:if>

                </c:forEach>
            </select>
            <input type="submit" value="Filter">
        </form>
        <a href="search?keyword=${param.keyword}">Reset</a>
    </div>

    <div id="content-right">   
        <div class="path">Search result</b></div>
        <div class="content-main card-layout">
            <c:forEach items="${product_list}" var="p">
                <div class="product card-layout__item">
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