
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>e-Commerce Store</title>
    </head>
    <body>
 Register account!
       <s:form action="registerUser">
            <s:textfield name="username" label="Enter your username " />
			<s:textfield name="password" label="Enter your password " />
			<s:textfield name="passwordRe" label="Confirm your password" />
			<s:textfield name="email" label="Enter your email" />
				   
            <s:submit />
        </s:form>
		 <p>Already have an account? <a href="jsp/login.jsp">Login</a></p>
    </body>
</html>











