<%@ page import="model.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 13.09.2022
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>my items</title>
</head>
<body>
<%
    List<Item> itemList = (List<Item>) request.getAttribute("items");
%>

<%for (Item item : itemList) {%>
<figure>
    <img src="/getItemImage?itemPic=<%=item.getPicUrl()%>" width="200">
    <figcaption>
        <div>title: <%=item.getTitle()%>
        </div>
        <div>category: <%=item.getCategory().getName()%>
        </div>
        <div>price: <%=item.getPrice()%>
        </div>
        <div>owner: <%=item.getUser().getName() %>
        </div>
        <a href="/remove/item?itemId=<%=item.getId()%>">remove item</a>
    </figcaption>
</figure>
<%}%>

</body>
</html>
