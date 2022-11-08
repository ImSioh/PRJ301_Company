<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="dal.*" %>
<%@page import="models.*" %>
<%@page import="java.util.*" %>
<%@include file="template/header.jsp" %>

<div id="content">
    <div id="cart">
        <div id="cart-title">
            <h3>SHOPPING CART</h3>
        </div>
        <%--<c:if test="${sessionScope.cartSession==null or sessionScope.cartSession.size()==0}">--%>
        <!--            <div id="empty-cart">
                        <img src="img/cart-empty.png" style="margin: 3% 0 0 30%"/><br/>
                        <a href="home" id="shop" 
                           style="border-radius: 10px;
                           background: saddlebrown;
                           color: white;
                           width: 10%;
                           text-align: center;
                           text-decoration: none;
                           padding: 10px;
                           margin: 3% 0 3% 45%;
                           display: block;">
                            SHOP NOW
                        </a>
                    </div>-->
        <%--</c:if>--%>
        <div id="cart-content">
            <c:set value="${sessionScope.cartSession}" var="cart"/>
            <c:forEach items="${cart.items}" var="c">
                <div class="cart-item">
                    <div class="cart-item-infor">
                        <div class="cart-item-img">
                            <a href="detail?pid=${c.product.getProductID()}"><img src="img/1.jpg" width="100%"/></a>
                            <!--<input type="hidden" name="productID" value="${c.product.getProductID()}">-->
                        </div>
                        <div class="cart-item-name">
                            <a href="detail?pid=${c.product.getProductID()}">${c.product.getProductName()}</a>
                            <!--<input type="hidden" name="productName" value="${c.product.getProductName()}">-->
                        </div>
                        <div class="cart-item-price">
                            <fmt:formatNumber pattern="##.##" value="${c.product.getUnitPrice() * c.quantity}"/> $
                            <!--<input type="hidden" name="unitPrice" value="${c.product.getUnitPrice()}">-->
                        </div>
                        <div class="cart-item-button">
                            <form action="cart-quantity" method="post">
                                <input type="hidden" name="pid" value="${c.product.getProductID()}"/>
                                <input type="submit" value="Remove"/>
                            </form>
                            <!--<a href="#">Remove</a>-->
                            <!--                            <form action="" name="f" method="post" >
                                                            <input type="hidden" name="pid" value="${c.product.getProductID()}"/>
                                                            <input type="button" value="Remove" onclick="removeCart(${c.product.getProductID()})"/>
                                                        </form>-->
                        </div>
                    </div>
                    <div class="cart-item-function">
                        <a href="cart-quantity?num=-1&pid=${c.product.getProductID()}">-</a>  
                        <a href="cart-quantity?num=1&pid=${c.product.getProductID()}">+</a>
                        <input type="text" name="quantity" value="${c.quantity}" disabled/>
                    </div>
                </div>
            </c:forEach>
        </div>
        <%--<c:if test="${sessionScope.cartSession!=null && sessionScope.cartSession.size()==0}">--%>
        <div id="cart-summary">
            <div id="cart-summary-content">
                Total amount: 
                <span style="color:red">
                    <fmt:formatNumber pattern="##.##" value="${totalMoney}"/>$
                </span>
            </div>
        </div>
        <form action="cart-order" method="post">
            <div id="customer-info">
                <div id="customer-info-content">
                    <h3>CUSTOMER INFORMATION:</h3>
                    <c:set value="${customer}" var="cus"/>
                    <div id="customer-info-detail">
                        <div id="customer-info-left">
                            <input type="text" name="compName" placeholder="Company name *" value="${cus.companyName}"/><br/>
                            <input type="text" name="contName" placeholder="Contact name *" value="${cus.contactName}"/><br/>
                        </div>
                        <div id="customer-info-right">
                            <input type="text" name="contTitle" placeholder="Contact title *" value="${cus.contactTitle}"/><br/>
                            <input type="text" name="address" placeholder="Address *" value="${cus.address}"/><br/>
                            Required Date<span style="color: red"> *</span>: <br/> 
                            <input type="date" name="requiredDate" value="${requiredDate}" min="${datemin}" max="${datemax}" defaultValue="${datemax}">
                        </div>
                    </div>
                </div>
            </div>
            <div id="customer-info">
                <div id="customer-info-content">
                    <h3>PAYMENT METHODS:</h3>
                    <div id="customer-info-payment">
                        <div>
                            <input type="radio" name="rbPaymentMethod" checked/>
                            Payment C.O.D - Payment on delivery
                        </div>
                        <div>
                            <input type="radio" name="rbPaymentMethod" disabled/>
                            Payment via online payment gateway
                        </div>
                    </div>
                </div>
            </div>
            <div id="cart-order">
                <!--<input type="button" value="CONFIRM ORDER"/>-->
                <input type="submit" value="CONFIRM ORDER"/>
            </div>
        </form>
        <%--</c:if>--%>
    </div>
</div>

<%@include file="template/footer.jsp" %>

<script type="text/javascript">
    function removeCart(pid) {
        document.f.action = "cart-quantity";
        document.f.submit();
    }
</script>