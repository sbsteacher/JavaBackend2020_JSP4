<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
	<div>
		<div>${msg}</div>
		<form id="frm" action="/join" method="post" onsubmit="return chk()">
			<input type="hidden1" id="checkId" value="2">
			<div>
				<input type="text" name="cid" placeholder="아이디" value="${data.cid}">
				<button onclick="return chkId()">아이디 중복확인</button>
			</div>
			<div id="duplicationIdMsg"></div>
			<div><input type="password" name="cpw" placeholder="비밀번호"  value="${data.cpw}"></div>
			<div><input type="password" name="recpw" placeholder="비밀번호 확인"  value="${data.cpw}"></div>
			<div><input type="text" name="nm" placeholder="이름"  value="${data.nm}"></div>
			<div><input type="submit" value="회원가입"></div>
		</form>
	</div>
	<script>
		function chkId() {
			var cid = frm.cid.value
			if(cid.length == 0) {
				alert('아이디를 입력해 주세요')
				return false
			}
			
			axios.get('/chkId', {
			    params: {
			      cid: cid
			    }
			  }).then(function (response) {
			    console.log(response);
			  
			  }).catch(function (error) {
			    console.log(error);
			  })
			
			var a = 10;
			return false
		}
	
		function chk() {
			if(frm.cid.value.length == 0) {
				alert('아이디를 입력해 주세요.')
				frm.cid.focus()
				return false
			} else if(frm.cpw.value == '') {
				alert('비밀번호를 입력해 주세요.')
				frm.cpw.focus()
				return false
			} else if(frm.cpw.value != frm.recpw.value) {
				alert('비밀번호를 확인해 주세요.')
				frm.cpw.focus()
				return false
			} else if(frm.nm.value.length == 0) {
				alert('이름을 입력해 주세요.')
				frm.nm.focus()
				return false
			}			
		}
	</script>
</body>
</html>