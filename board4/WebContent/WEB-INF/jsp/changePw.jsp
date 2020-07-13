<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
</head>
<body>
	<div>${msg}</div>
	<form action="/myPage?typ=${param.typ}" method="post">
		<div>기존 비밀번호: <input type="password" name="currentPw"></div>
		<div>변경 비밀번호: <input type="password" name="changePw"></div>
		<div>확인 비밀번호: <input type="password" name="changePwConfirm"></div>
		<div><input type="submit" value="변경"></div>
	</form>
</body>
</html>