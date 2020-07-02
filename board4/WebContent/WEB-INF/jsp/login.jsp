<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
	<div id="container">
		<div>${msg}</div>
		<form id="frm" action="/login" method="post">
			<div><input type="text" name="cid" placeholder="id" value="${writedCid}"></div>
			<div><input type="password" name="cpw" placeholder="password"></div>
			<div><input type="submit" value="Login"></div>
			<div><a href="/join">회원가입</a></div>
		</form>
	</div>
</body>
</html>