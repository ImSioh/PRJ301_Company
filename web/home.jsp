<%@page import="java.util.ArrayList" %>
<%@page import="dal.*" %>

<%@include file="template/header.jsp" %>

<div id="content">
    <div id="content-left">
        <h3>CATEGORY</h3>

        <%
            ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("category");
            for(Category c : list) {
        %>
        <ul>
            <a href="category?id=${c.getCategoryID()}"><li><%= c.getCategoryName() %></li></a>
        </ul>
        <%
            }
        %>
    </div>
    <div id="content-right">
        <div class="path">Hot</b></div>
        <div class="content-main">
            <div class="product">
                <a href="detail.jsp"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="detail.jsp">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
            <div class="product">
                <a href=""><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
            <div class="product">
                <a href=""><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
            <div class="product">
                <a href=""><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
        </div>
        <div class="path">Best Sale</b></div>
        <div class="content-main">
            <div class="product">
                <a href="detail.jsp"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
            <div class="product">
                <a href=""><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
            <div class="product">
                <a href=""><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
            <div class="product">
                <a href=""><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
        </div>
        <div class="path">New Product</b></div>
        <div class="content-main">
            <div class="product">
                <a href="detail.jsp"><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
            <div class="product">
                <a href=""><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
            <div class="product">
                <a href=""><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
            <div class="product">
                <a href=""><img src="img/1.jpg" width="100%"/></a>
                <div class="name"><a href="">Product 1</a></div>
                <div class="price">$1000</div>
                <div><a href="">Buy now</a></div>
            </div>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp" %>