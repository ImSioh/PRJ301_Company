<%@page import="java.util.ArrayList" %>
<%@page import="models.*" %>
<%@page import="dal.*" %>

<%@include file="template/header.jsp" %>

<div id="content">
    <c:set value="${category}" var="c"></c:set>
    <c:set value="${product}" var="p"></c:set>
        <div id="content-detail">
            <div id="content-title">
                <a href="home">Home</a> >
                <a href="category?id=${c.categoryID}">${c.categoryName}</a> >
            Model: SP${p.getProductID()}
        </div>
        <div id="product">
            <div id="product-name">
                <h2>${p.productName}</h2>
                <div id="product-detail">
                    <div id="product-detail-left">
                        <div id="product-img">
                            <img src="img/1.jpg"/>
                        </div>
                        <div id="product-img-items">
                            <div><a href="#"><img src="img/1.jpg"/></a></div>
                            <div><a href="#"><img src="img/1.jpg"/></a></div>
                            <div><a href="#"><img src="img/1.jpg"/></a></div>
                            <div><a href="#"><img src="img/1.jpg"/></a></div>
                        </div>
                    </div>
                    <div id="product-detail-right">
                        <div id="product-detail-right-content">
                            <div id="product-price">$ ${p.unitPrice}</div>
                            <div id="product-status">
                                <c:if test="${p.unitsInStock>0}">
                                    <span style="color: ">In stock</span>
                                </c:if>
                                <c:if test="${p.unitsInStock<=0}">
                                    <span style="color: black">Out of stock</span>
                                </c:if>
                            </div>

                            <c:if test="${sessionScope.accSession==null || sessionScope.accSession.role==2}">
                                <div id="product-detail-buttons">
                                    <div id="product-detail-button">
                                        <a href="cart"><input type="button" value="BUY NOW"></a>
<!--                                    </div>
                                    <div id="product-detail-button">-->
<!--                                        <form action="add-to-cart?num=1&pid=${p.productID}" method="post">
                                            <input type="submit" value="ADD TO CART" style="background-color: #fff; color:red;border: 1px solid gray;">
                                        </form>-->
                                        <form action="" name="f" method="post">
                                            <input type="button" value="ADD TO CART" onclick="addtocart(${p.productID})" style="background-color: #fff; color:red;border: 1px solid gray;">
                                        </form>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="info-detail">
            <div id="info-detail-title">
                <h2>Information detail</h2>
                <div style="margin:10px auto;">
                    ${c.description} <br>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio eligendi ratione vitae nobis numquam dolorum assumenda saepe enim cumque blanditiis, deleniti neque voluptate vel ducimus in omnis harum aut nisi.
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="template/footer.jsp" %>

<script type="text/javascript">
    function addtocart(pid) {
        document.f.action = "add-to-cart?num=1&pid=" + pid;
        document.f.submit();
    }
</script>