<%@page import="java.util.ArrayList" %>
<%@page import="dal.*" %>

<%@include file="template/header.jsp" %>

<div id="content">
    <div id="content-left">
        <h3>CATEGORY</h3>
        <c:forEach items="${category}" var="c">
            <ul>
                <a href="category?id=${c.getCategoryID()}"><li>${c.getCategoryName()}</li></a>
            </ul>
            <!--${c.getCategoryName()}-->
        </c:forEach>

        <ul>
            <a href="category.jsp"><li>Category 1</li></a>
            <a href="#"><li>Category 2</li></a>
            <a href="#"><li>Category 3</li></a>
            <a href="#"><li>Category 4</li></a>
        </ul>

    </div>
    <div id="content-right">
        All product of Category 1

    </div>
    <%@include file="template/footer.jsp" %>