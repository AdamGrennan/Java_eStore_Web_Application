
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
Login to e-Commerce shop
       <s:form action="loginUser">
	   		<s:textfield name="username" label="Enter your username" />
			<s:textfield name="password" label="Enter your password " />
				   
            <s:submit />
        </s:form>
			 <p>No account? <a href="jsp/index.jsp">Register</a></p>
    </body>
</html>











