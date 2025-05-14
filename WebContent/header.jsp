<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>得点管理システム</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="header">
        <h1>得点管理システム</h1>
        <% if (session.getAttribute("loggedInUser") != null) { %>
            <span class="user-info">
                <%= session.getAttribute("loggedInUser") %>様
            </span>
            <span class="logout">
                <a href="logout.jsp">ログアウト</a>
            </span>
        <% } %>
    </div>

    </body>
</html>