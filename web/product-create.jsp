<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@include file="template/header2.jsp" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

<div id="content-right">
    <div class="path-admin">CREATE A NEW PRODUCT</b></div>
    <div class="content-main">
        <form id="content-main-product" method="post">
            <div class="content-main-1">
                <label>Product name (*):</label><br/>
                <input type="text" name="txtProductName" id=""><br/>
                <c:if test="${msgProductName!=null}">
                    <span class="msg-error">${msgProductName}</span><br/>
                </c:if>

                <label>Unit price:</label><br/>
                <input type="text" name="txtUnitPrice" id=""><br/>
                <c:if test="${msgUnitPrice!=null}">
                    <span class="msg-error">${msgUnitPrice}</span><br/>
                </c:if>

                <label>Quantity per unit:</label><br/>
                <input type="text" name="txtQuantityPerUnit" id=""><br/>
                <c:if test="${msgQuantityPerUnit!=null}">
                    <span class="msg-error">${msgQuantityPerUnit}</span><br/>
                </c:if>

                <label>Units in stock (*):</label><br/>
                <input type="text" name="txtUnitsInStock" id=""><br/>
                <c:if test="${msgUnitsInStock!=null}">
                    <span class="msg-error">${msgUnitsInStock}</span><br/>
                </c:if>
            </div>

            <div class="content-main-1">
                <label>Category (*):</label><br/>
                <select name="ddlCategory">
                    <c:forEach items="${category}" var="c">
                        <option value="${c.getCategoryID()}">${c.getCategoryName()}</option>
                    </c:forEach>
                </select>
                <br/>
                <c:if test="${msgCategory==null}">
                    <span class="msg-error">${msgCategory}</span><br/>
                </c:if>

                <label>Reorder level:</label><br/>
                <input type="text" name="txtReorderLevel" id=""><br/>
                <c:if test="${msgReorderLevel!=null}">
                    <span class="msg-error">${msgReorderLevel}</span><br/>
                </c:if>

                <label>Units on order:</label><br/>
                <input type="text" name="txtUnitsOnOrder" id="" disabled><br/>

                <label>Discontinued:</label><br/>
                <input type="checkbox" name="chkDiscontinued" id="" value="true"><br/>

                <input type="submit" value="Save"/>
            </div>
        </form>
    </div>
</div>

<%@include file="template/footer2.jsp" %>
