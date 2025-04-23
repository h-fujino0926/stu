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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% if (session.getAttribute("loggedInUser") != null) { %>
    <div class="sidebar">
        <nav class="navigation">
            <ul>
                <li><a href="menu.jsp">メニュー</a></li>
                <li><a href="student_list.jsp">学生管理</a></li>
                <li>
                    <label>成績管理</label>
                    <ul>
                        <li><a href="grade_register.jsp">成績登録</a></li>
                        <li><a href="grade_reference.jsp">成績参照</a></li>
                    </ul>
                </li>
                <li><a href="subject_list.jsp">科目管理</a></li>
            </ul>
        </nav>
    </div>
<% } %>