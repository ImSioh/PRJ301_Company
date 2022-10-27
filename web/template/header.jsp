<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dal.*" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ABC Company</title>
        <% String path = request.getContextPath(); %>
        <link rel="stylesheet" href="<%= path %>/css/style.css"/>
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>" />
        <link rel="icon" href="<c:url value="/img/logo.png"/>" />
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="<%= path %>/home"><img src="<%= path %>/img/logo.png"/></a>
                </div>
                <div id="banner">
                    <ul>
                        <li><a href="<%= path %>/cart">Cart: 0</a></li>
                            <%
                                if(session.getAttribute("accSession")==null){
                            %>
                        <li><a href="<%= path %>/account/signin">SignIn</a></li>
                        <li><a href="<%= path %>/account/signup">SignUp</a></li>
                            <% 
                                } else {
                                    Account acc = (Account)request.getSession().getAttribute("accSession");
                            %>
                        <li><a href="<%= path %>/account/profile">Profile (<%= acc.getEmail() %>)</a></li>
                        <li><a href="<%= path %>/account/signin">SignOut</a></li>
                            <%
                                }
                            %>
                    </ul>
                </div>
            </div>