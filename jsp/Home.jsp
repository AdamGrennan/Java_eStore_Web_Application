
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
		
<s:form action="viewProfile">
<s:submit value="View Profile"/>
</s:form>

<s:form action="searchProfiles">
<s:submit value="View Other Profiles"/>
</s:form>

<s:form action="viewAllProfile">
<s:submit value="View All Profiles"/>
</s:form>

<s:form action="viewAddItem">
<s:submit value="Add Item For Sale"/>
</s:form>

<s:form action="viewAllItems">
<s:submit value="View All Items"/>
</s:form>


 <s:form action="allBids">
<s:submit value="View Bids on an item"/>
</s:form> 	

<s:form action="logoff">
    <s:hidden name="logOff" value="logOffUser" />
    <s:submit value="Logoff"/>
</s:form>


    </body>
</html>











