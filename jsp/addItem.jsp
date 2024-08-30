
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
Add item for sale!
  <s:form action="addItem">
     <s:hidden name="seller" value="%{#session.currentUser}" />
    <s:textfield name="itemName" label="Item name:" />
    <s:textarea name="description" label="Item description:" cols="40" rows="10" />
    <s:submit />
</s:form>


	<p><a href="jsp/Home.jsp">Home</a></p>
    </body>
</html>











