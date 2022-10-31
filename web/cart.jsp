<%@include file="template/header.jsp" %>

<div id="content">
    <div id="cart">
        <div id="cart-title">
            <h3>SHOPPING CART</h3>
        </div>
        <div id="cart-content">
            <div class="cart-item">
                <div class="cart-item-infor">
                    <div class="cart-item-img">
                        <img src="img/1.jpg"/>
                    </div>
                    <div class="cart-item-name">
                        <a href="detail.jsp?id=1">Product 1</a>
                    </div>
                    <div class="cart-item-price">
                        1000 $
                    </div>
                    <div class="cart-item-button">
                        <a href="#">Remove</a>
                    </div>
                </div>
                <div class="cart-item-function">
                    <a href="#">-</a>  
                    <a href="#">+</a>
                    <input type="text" value="1" disabled/>
                </div>
            </div>
            <div class="cart-item">
                <div class="cart-item-infor">
                    <div class="cart-item-img">
                        <img src="img/1.jpg"/>
                    </div>
                    <div class="cart-item-name">
                        <a href="detail.jsp?id=1">Product 1</a>
                    </div>
                    <div class="cart-item-price">
                        1000 $
                    </div>
                    <div class="cart-item-button">
                        <a href="#">Remove</a>
                    </div>
                </div>
                <div class="cart-item-function">
                    <a href="#">-</a>  
                    <a href="#">+</a>
                    <input type="text" value="2" disabled/>
                </div>
            </div>
            <div class="cart-item">
                <div class="cart-item-infor">
                    <div class="cart-item-img">
                        <img src="img/1.jpg"/>
                    </div>
                    <div class="cart-item-name">
                        <a href="detail.jsp?id=1">Product 1</a>
                    </div>
                    <div class="cart-item-price">
                        1000 $
                    </div>
                    <div class="cart-item-button">
                        <a href="#">Remove</a>
                    </div>
                </div>
                <div class="cart-item-function">
                    <a href="#">-</a>  
                    <a href="#">+</a>
                    <input type="text" value="1" disabled/>
                </div>
            </div>
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