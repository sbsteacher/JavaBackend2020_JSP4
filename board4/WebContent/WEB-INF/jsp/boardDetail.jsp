<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일</title>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
	<div>
		<a href="/boardList"><button>리스트로 돌아가기</button></a>
		<c:if test="${loginUser.i_user == data.i_user }">
			<a href="/boardDetail?i_board=${data.i_board}&typ=mod"><button>수정</button></a>
			<a href="/boardDel?i_board=${data.i_board}"><button>삭제</button></a>
		</c:if>
	</div>
	<div>
		<button onclick="doLike(${data.i_board})">
			<span id="markLike">
				<c:if test="${data.likeUser > 0 }">♥</c:if>
				<c:if test="${data.likeUser == 0 }">♡</c:if>
			</span>
		</button>
	</div>
	<div>
		${msg }
	</div>
	<div>
		조회수: ${data.cnt }
	</div>
	<div>
		${data.title }, ${data.ctnt }, ${data.r_dt }, ${data.userNm }
	</div>
	<script>		
		function doLike(i_board) {
			
			var isLike = (markLike.innerHTML.trim() == '♥') ?  1 : 0
			
			axios.get('/boardLike', {
				params: {
					'i_board': i_board,
					'isLike': isLike
				}
			}).then(function(res) {
				if(res.data.result == 1) { //잘 처리 됨!!!
					/*
					if(markLike.innerHTML == '♥') {
						markLike.innerHTML = '♡' 
					} else {
						markLike.innerHTML = '♥'
					}
					*/
					markLike.innerHTML = (markLike.innerHTML.trim() == '♥') ?  '♡' : '♥'
				}
				
				/*
				if(res.data.result == 1) {
					markLike.innerHTML = '♥'
				} else {
					markLike.innerHTML = '♡'
				}
				*/
				//console.log(JSON.stringify(res))
			})
		}
	</script>
</body>
</html>