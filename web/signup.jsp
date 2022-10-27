<%@include file="template/header.jsp" %>

<div id="content">
    <div id="form">
        <div id="form-title">
            <span><a href="<%=request.getContextPath()%>/account/signup" style="color: red;">SIGN UP</a></span>
            <span><a href="<%=request.getContextPath()%>/account/signin">SIGN IN</a></span>
        </div>

        <div id="form-content">
            <form method="post">
                <!--Company Name-->
                <label>Company name<span style="color: red;">*</span></label><br/>
                <input type="text" name="copaName"/><br/>
                <%
                if(request.getAttribute("msgCPN")!=null) {
                %>
                <span class="msg-error">
                    <%
                        out.print(request.getAttribute("msgCPN"));
                    %>
                </span><br/>
                <%
                }
                %>

                <!--Contact Name-->
                <label>Contact name<span style="color: red;">*</span></label><br/>
                <input type="text" name="contName"/><br/>
                <%
                if(request.getAttribute("msgCTN")!=null) {
                %>
                <span class="msg-error">
                    <%
                        out.print(request.getAttribute("msgCTN"));
                    %>
                </span><br/>
                <%
                }
                %>

                <!--Contact Title-->
                <label>Contact title<span style="color: red;">*</span></label><br/>
                <input type="text" name="contTitle"/><br/>
                <%
                if(request.getAttribute("msgCTT")!=null) {
                %>
                <span class="msg-error">
                    <%
                        out.print(request.getAttribute("msgCTT"));
                    %>
                </span><br/>
                <%
                }
                %>

                <!--Address-->
                <label>Address<span style="color: red;">*</span></label><br/>
                <input type="text" name="Addr"/><br/>
                <%
                if(request.getAttribute("msgADR")!=null) {
                %>
                <span class="msg-error">
                    <%
                        out.print(request.getAttribute("msgADR"));
                    %>
                </span><br/>
                <%
                }
                %>

                <!--Email-->
                <label>Email<span style="color: red;">*</span></label><br/>
                <input type="text" name="Email"/><br/>
                <%
                if(request.getAttribute("msgE")!=null) {
                %>
                <span class="msg-error">
                    <%
                        out.print(request.getAttribute("msgE"));
                    %>
                </span><br/>
                <%
                }
                %>

                <!--Password-->
                <label>Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="Pass"/><br/>
                <%
                if(request.getAttribute("msgP")!=null) {
                %>
                <span class="msg-error">
                    <%
                        out.print(request.getAttribute("msgP"));
                    %>
                </span><br/>
                <%
                }
                %>

                <!--Re-password-->
                <label>Re-Password<span style="color: red;">*</span></label><br/>
                <input type="password" name="rePass"/><br/>
                <%
                if(request.getAttribute("msgRP")!=null) {
                %>
                <span class="msg-error">
                    <%
                        out.print(request.getAttribute("msgRP"));
                    %>
                </span><br/>
                <%
                }
                %>

                <!--Sign up-->
                <div></div>
                <input type="submit" value="SIGN UP" style="margin-bottom: 30px;"/>
            </form>
        </div>
    </div>
</div>
                
<%@include file="template/footer.jsp" %>