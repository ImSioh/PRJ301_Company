<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="template/header.jsp" %>

<%@include file="template/profile_left.jsp" %>

<div id="content-right">
    <div class="path">Personal information</b></div>
    <div class="content-main">
        <div id="profile-content">
            <div class="profile-content-col">
                <form method="post">
                    <div>Customer ID: <br/><input type="text" name="cusID" value="${cus.customerID}" readonly></div>
                    <div>Company name: <br/><input type="text" name="compName" value="${cus.companyName}"></div>
                    <div>
                        <input type="submit" value="Save info"/>
                    </div>
            </div>
            <div class="profile-content-col">
                <div>Contact name: <br/><input type="text" name="contName" value="${cus.contactName}"></div>
                <div>Company title: <br/><input type="text" name="contTitle" value="${cus.contactTitle}"></div>
                <!--<div>Password: <br/><input type="password" name="pass" value="${acc.password}"></div>-->
            </div>
            <div class="profile-content-col">
                <div>Address: <br/><input type="text" name="address" value="${cus.address}"></div>
                <div>Email: <br/><input type="email" name="email" value="${acc.email}" readonly></div>
                <!--<div>Re-password: <br/><input type="password" name="re-pass" value="${acc.password}"></div>-->
            </div>
            </form>
        </div>
    </div>
</div>
</div>

<%@include file="template/footer.jsp" %>