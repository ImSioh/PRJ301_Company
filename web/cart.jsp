<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="template/header.jsp" %>

<div id="content">
    <div id="cart">
        <div id="cart-title">
            <h3>SHOPPING CART</h3>
        </div>
        <div id="cart-content">
            <c:forEach items="${product}" var="p" begin="0" end="4">
                <div class="cart-item">
                    <div class="cart-item-infor">
                        <div class="cart-item-img">
                            <a href="detail?pid=${p.getProductID()}"><img src="img/1.jpg" width="100%"/></a>
                            <input type="hidden" name="productID" value="${p.getProductID()}">
                        </div>
                        <div class="cart-item-name">
                            <a href="detail?pid=${p.getProductID()}">${p.getProductName()}</a>
                            <input type="hidden" name="productName" value="${p.getProductName()}">
                        </div>
                        <div class="cart-item-price">
                            ${p.getUnitPrice()} $
                            <input type="hidden" name="unitPrice" value="${p.getUnitPrice()}">
                        </div>
                        <div class="cart-item-button">
                            <a href="#">Remove</a>
                        </div>
                    </div>
                    <div class="cart-item-function">
                        <a href="#">-</a>  
                        <a href="#">+</a>
                        <input type="text" name="quantity" value="2" disabled/>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div id="cart-summary">
            <div id="cart-summary-content">Total amount: <span style="color:red">3000 $</span></div>
        </div>
        <form action="cart" method="post">
            <div id="customer-info">
                <div id="customer-info-content">
                    <h3>CUSTOMER INFORMATION:</h3>
                    <c:set value="${customer}" var="cus"></c:set>
                        <div id="customer-info-detail">
                            <div id="customer-info-left">
                                <input type="text" name="compName" placeholder="Company name *" value="${cus.companyName}"/><br/>
                            <input type="text" name="contName" placeholder="Contact name *" value="${cus.contactName}"/><br/>
                        </div>
                        <div id="customer-info-right">
                            <input type="text" name="contTitle" placeholder="Contact title *" value="${cus.contactTitle}"/><br/>
                            <input type="text" name="contTitle" placeholder="Address *" value="${cus.address}"/><br/>
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
                <input type="button" value="CONFIRM ORDER"/>
            </div>
        </form>
    </div>
</div>

<%@include file="template/footer.jsp" %>