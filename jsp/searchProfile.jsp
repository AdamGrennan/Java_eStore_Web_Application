
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
	Logged in as <s:property value="#session.currentUser" /> <br/>	
<h1>Search for profiles!</h1>
<s:form action="searchUser" method="post">
    <s:textfield name="searchUser" label="Search username" />
    <s:submit />
</s:form>

<p><a href="jsp/Home.jsp">Home</a></p>
    </body>
</html>











