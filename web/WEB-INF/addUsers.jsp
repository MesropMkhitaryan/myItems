<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.09.2022
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%
    String msg = (String) request.getAttribute("msg");
%>

<%  if (msg != null) { %>

<p style="color: red"><%= msg%> </p>
<%}%>

<form action="/users/add" method="post" >
    <input type="text" name = "name" placeholder="Input name"><br>
    <input type="text" name = "surname" placeholder="Input surname"><br>
    <input type="email" name = "email" placeholder="Input email"><br>
    <input type="password" name = "password" placeholder="Input password"><br>
    <input type="submit" value="register"/>
</form>
</body>
</html>
