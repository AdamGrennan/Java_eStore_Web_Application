

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
	Logged in as <s:property value="#session.currentUser" /> <br/>	
        <h1>Profile Details</h1>
    Username: <s:property value="searchUser" /> <br/>
    Email: <s:property value="searchEmail" /> <br/>
		
<p><a href="jsp/Home.jsp">Home</a></p>
    </body>
</html>
