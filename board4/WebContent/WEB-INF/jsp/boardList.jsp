<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
	.myCtnt {
		color: #1abc9c;
	}
</style>
</head>
<body>
	<div>리스트!!</div>
	<div>
		<table>
			<tr>
				<th>no</th>
				<th>제목</th>
				<th>등록일시</th>
				<th>작성자</th>
			</tr>
			<c:forEach var="item" items="${data }">
				<tr>
					<td class="${ (item.i_user == loginUser.i_user) ? 'myCtnt' : ''    }">${item.i_board}</td>
					<td>${item.title }</td>
					<td>${item.r_dt }</td>
					<td>${item.userNm }</td>
				</tr>
			</c:forEach>
		</table>
	</div>	
</body>
</html>