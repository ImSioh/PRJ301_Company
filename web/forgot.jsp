<%@include file="template/header.jsp" %>

<div id="content">
    <div id="form">
        <h3 style="padding: 20px;">Forgot your account password?</h3>
        <div style="padding: 0px 20px 10px;">
            Please enter the email address registered with us to create a new password. We will send an email to the email address provided and require verification before we can generate a new password
        </div>
        <div id="form-content">
            <form action="" method="post">
                <label>Enter your registered email address<span style="color: red;">*</span></label><br/>
                <input type="text"/><br/>
                <input type="submit" value="GET PASSWORD" style="margin-bottom: 30px;"/><br/>
            </form>
        </div>
    </div>

    <%@include file="template/footer.jsp" %>