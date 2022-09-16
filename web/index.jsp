<%@ page import="model.Item" %>
<%@ page import="model.Category" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="manager.CategoryManager" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.09.2022
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    CategoryManager categoryManager = new CategoryManager();
    User user = (User) session.getAttribute("user");
    List<Item> itemList = (List<Item>) request.getAttribute("items");
    List<Category> listByCat =  categoryManager.getAll();
%>

<a href="/items?categoryId=0">Main</a>
<% for (Category category : listByCat) { %>
    <a href="/items?categoryId=<%=category.getId()%>"><%=category.getName()%></a>

<%}%>

<% if
(
        user
                !=
                null
) {%>
<p>Hello <%=user
        .
        getName
                (
                )%>
</p>

<a href="/items/add">Add Items</a>|
<a href="/logout">Logout</a>
<a href="/my/items?userId=<%=user.getId()%>">my items </a>

<%
} else {
%>
<a href="/users/add">Register</a>
<a href="/login">Login</a>
<%}%>


<%
    if
    (
            itemList
                    !=
                    null
    ) {
%>
<% for
(
        Item
                item
        :
        itemList
) {%>

<figure>
    <% if
    (
            item
                    .
                    getPicUrl
                            (
                            )
                    ==
                    null
                    ||
                    item
                            .
                            getPicUrl
                                    (
                                    )
                                    .
                            length
                                    (
                                    )
                            ==
                            0
    ) {%>
    <img src="/img/default.png" width="100">
    <% } else {%>
    <img src="/getItemImage?itemPic=<%=item.getPicUrl()%>" width="200">
    <% }%>

    <figcaption>
        <div>title : <%= item
                .
                getTitle
                        (
                        )%>
        </div>
        <div>category : <%= item
                .
                getCategory
                        (
                        )
                        .
                getName
                        (
                        )%>
        </div>
        <div>price : <%= item
                .
                getPrice
                        (
                        )%>
        </div>
        <div>owner : <%= item
                .
                getUser
                        (
                        )
                        .
                getName
                        (
                        )%>
        </div>
        <% if
        (
                item
                        .
                        getUser
                                (
                                )
                                .
                        equals
                                (
                                        user
                                )
        ) {%>
        <a href="/remove/item?itemId=<%=item.getId()%>">remove item</a>
        <%}%>
    </figcaption>
</figure>
<% }%>
<% }%>


</body>
</html>
