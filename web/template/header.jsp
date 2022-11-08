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

        <style>
            #header .header-search {
                width: 100%;
                margin-left: 20%;
            }

            #header .header-search {
                padding-top: 40px;
            }

            #header .header-search input[type="text"] {
                line-height: 30px;
                border-radius: 10px;
                border: 1px solid saddlebrown;
                width: 80%;
                padding-left: 10px;
            }

            #header .header-search input[type="submit"]{
                width: 60px;
                height: 30px;
                background: saddlebrown;
                color: white;
                font-weight: bold;
                border-radius: 7.5px;
                border: 1px solid white;
            }
        </style>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <div id="logo">
                    <a href="<%= path %>/home"><img src="<%= path %>/img/logo.png"/></a>
                </div>
                <%--<c:if test="${pageContext.request.requestURL != 'http://localhost:9999/PRJ301_Company/account/../signin.jsp'
                              && pageContext.request.requestURL != 'http://localhost:9999/PRJ301_Company/account/../signup.jsp'
                              && pageContext.request.requestURL != 'http://localhost:9999/PRJ301_Company/forgot.jsp'}">--%>
                <div class="header-search">
                    <form action="search" id="usersearch">
                        <input style="font-style: italic" type="text" name="keyword" placeholder="Which product do you want to find?...">
                        <input type="submit" value="Search">
                    </form>
                </div>
                <%--</c:if>--%>
                <div id="banner">
                    <ul>
                        <li>
                            <img src="<%= path %>/img/cart.png" width="25" height="25" alt="cart"/>
                            <a href="<%= path %>/cart">Cart: ${size}</a>
                        </li>
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