<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>My Bids</title>
</head>
<body>
Logged in as <s:property value="#session.currentUser" /> <br/>	
    <h2>My Bids</h2>    
	
    <s:iterator value="myBids">
        <p>
            Item Name: <s:property value="itemName" /><br/>
            Bid Amount: <s:property value="price" /><br/>
				<s:hidden name="bidder" value="%{#session.currentUser}" />		

        </p>
    </s:iterator>
	<p><a href="jsp/Home.jsp">Home</a></p>
	<p><a href="jsp/profile.jsp">Back</a></p>
</body>
</html>