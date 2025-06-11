<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>科目管理</h2>
<a href="SubjectCreate.action">新規登録</a>
<table class="table table-hover">
	<tr>
		<th>科目コード</th>
		<th>科目名</th>
	</tr>
	<c:forEach var="subject" items="${SUBJECT}">
		<tr>
			<td>${SUBJECT.CD}</td>
			<td>${SUBJECT.NAME}</td>
			<td><a href="SubjectUpdate.action?no=${SUBJECT.CD}">変更</a></td>
			<td><a href="SubjectDelete.action?no=${SUBJECT.CD}">削除</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>