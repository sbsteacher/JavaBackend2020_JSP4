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
	
	.boardItem:hover {
		background-color: #ecf0f1;
		cursor: pointer;
	}
</style>
</head>
<body>	
	<div>${loginUser.nm }님 환영합니다.</div>
	<div>
		<a href="boardReg"><button>글쓰기</button></a>
	</div>
	<div>
		<table>
			<tr>
				<th>no</th>
				<th>제목</th>
				<th>등록일시</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${data }" var="item">
				<tr class="boardItem" onclick="moveToDetail(${item.i_board})">
					<td class="${ (item.i_user == loginUser.i_user) ? 'myCtnt' : ''    }">${item.i_board}</td>
					<td>${item.title }</td>
					<td>${item.r_dt }</td>
					<td>${item.userNm }</td>
					<td>${item.cnt }</td>
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



