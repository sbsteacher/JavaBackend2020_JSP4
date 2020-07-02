<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/common.css">
<style>
	.boardItem:hover {
		background-color: #bdc3c7;
		cursor: pointer;
	}
</style>
</head>
<body>
	<div>		
		${loginUser.nm}님 환영합니다! 
		<a href="/boardReg"><button>글쓰기</button></a>
		<form action="/boardList" method="post">
			<input type="submit" value="로그아웃">
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>no</th>
				<th>제목</th>
				<th>작성일</th>
				<th>작성자</th>
			</tr>
			<c:forEach var="item" items="${data}">
				<tr class="boardItem" onclick="moveToDetail(${item.i_board})">
					<td>${item.i_board}</td>
					<td>${item.title }</td>
					<td>${item.r_dt }</td>
					<td>${item.userNm }</td>
				</tr>
			</c:forEach>
		</table>
	</div> 
	<script>
		function moveToDetail(i_board) {
			location.href = '/boardDetail?i_board=' + i_board
		}
	</script>
</body>
</html>



