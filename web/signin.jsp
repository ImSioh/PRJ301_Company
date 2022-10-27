<%@include file="template/header.jsp" %>

<div id="content">
    <div id="form">
        <div id="form-title">
            <span><a href="signup">SIGN UP</a></span>
            <span><a href="signin" style="color: red;">SIGN IN</a></span>
        </div>

        <div id="form-content">
            <div style="color: red; text-align: center">
                <%
                    if(request.getAttribute("msg")!=null) {
                        out.print(request.getAttribute("msg"));
                    }
                %>
            </div>
            <form method="post">
                <!--Email-->
                <label>Email<span style="color: red;">*</span></label><br/>
                <input type="text" name="Email"/><br/>
                <%
                if(request.getAttribute("msgEmail")!=null) {
                %>
                <span class="msg-error">
                    <%
                        out.print(request.getAttribute("msgEmail"));
                    %>
                </span><br/>
                <%
                }
                %>

                <!--Password-->
                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="Pass"/><br/>

                <%
                if(request.getAttribute("msgPass")!=null) {
                %>
                <span class="msg-error">
                    <%
                        out.print(request.getAttribute("msgPass"));
                    %>
                </span><br/>
                <%
                }
                %>

                <!--Forgot-->
                <div><a href="<c:url value="/forgot.jsp"/>">Forgot password?</a></div>
                <input type="submit" name="signin" value="SIGN IN"/><br/>
                <input type="button" value="FACEBOOK LOGIN" style="background-color: #3b5998;"/><br/>
                <input type="button" value="ZALO LOGIN" style="background-color: #009dff;margin-bottom: 30px;"/>
            </form>
        </div>
    </div>

    <%@include file="template/footer.jsp" %>