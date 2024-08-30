<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>All Users</title>
</head>
<body>
Logged in as <s:property value="#session.currentUser" /> <br/>	
    <h2>All Users</h2>
    
        <s:iterator value="userList">
            <p>
                Username: <s:property value="username" />
                Email: <s:property value="email" />
            </p>
        </s:iterator>

<p><a href="jsp/Home.jsp">Home</a></p>
</body>
</html>
