<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>All Items</title>
</head>
<body>
Logged in as <s:property value="#session.currentUser" /> <br/>	
    <h2>All Items!</h2>
    <s:iterator value="itemList">
        <p>
            Item: <s:property value="itemName" /> <br/>
            Seller: <s:property value="seller" /> <br/>
            Description: <s:property value="description" /> <br/>

          
        <s:form action="submitBid" method="post">
    <s:hidden name="itemName" value="%{itemName}" />
    <s:hidden name="bidder" value="%{#session.currentUser}" />
    Bid Amount: <s:textfield name="price" />
    <s:submit value="Submit Bid" />
</s:form>

        </p>
    </s:iterator>
<p><a href="jsp/Home.jsp">Home</a></p>
</body>
</html>