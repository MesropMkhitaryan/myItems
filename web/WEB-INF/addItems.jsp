<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="manager.UserManager" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.09.2022
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    HttpSession session1 = request.getSession();
    User user = (User) session1.getAttribute("user");

    List<Category> categories = (List<Category>) request.getAttribute("category");
%>

<form action="/items/add" method="post" enctype="multipart/form-data">

    <input type="text" name="title" placeholder="Input title"><br>
    <input type="number" name="price" placeholder="Input price"><br>
    <select name="categoryId">
        <%for (Category category : categories) { %>
        <option value="<%=category.getId()%>"><%= category.getName()%>
        </option>
        <% } %>
    </select>
    <input type="file" name="itemPic"/>
    <br>
    <input type="hidden" name="userId" value="<%= user.getId()%>"/>


    <input type="submit" value="add"/>
</form>
</body>
</html>
