<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록/수정</title>
</head>
<body>
	<div>
		<form action="/boardReg" method="post">
			<div><input type="hidden" name="i_board" value="${data.i_board}"></div>
			<div>제목: <input type="text" name="title" value="${data.title}"></div>
			<div>내용: <textarea name="ctnt">${data.ctnt}</textarea></div>
			<div><input type="submit" value="등록/수정"></div>
		</form>
	</div>
</body>
</html>