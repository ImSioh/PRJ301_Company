<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="template/header.jsp" %>

<%@include file="template/profile_left.jsp" %>

<div id="content-right">
    <div class="path">Personal information</b></div>
    <div class="content-main">
        <div id="profile-content">
            <div class="profile-content-col">
                <div>Company name: <br/>${cus.companyName}</div>
                <div>Contact name: <br/>${cus.contactName}</div>
                <div>
                    <a href="profile-edit?id=${cus.customerID}">
                        <input type="submit" value="Edit info"/></a>
                </div>
            </div>
            <div class="profile-content-col">
                <div>Company title: <br/>${cus.contactTitle}</div>
                <div>Address: <br/>${cus.address}</div>
            </div>
            <div class="profile-content-col">
                <div>Email: <br/>${acc.email}</div>
            </div>
        </div>
    </div>
</div>
</div>

<%@include file="template/footer.jsp" %>