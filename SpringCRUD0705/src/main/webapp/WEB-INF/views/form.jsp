<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<form>
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th>아이디</th><td></td>
				</tr>
				<tr>
					<th>비밀번호</th><td></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th><td></td>
				</tr>
				<tr>
					<th>생년월일</th><td></td>
				</tr>
				<tr>
					<th>성별</th><td></td>
				</tr>
				<tr>
					<th>이메일</th><td></td>
				</tr>
				<tr>
					<th>연락처</th><td></td>
				</tr>
				<tr>
					<th>아이디</th><td></td>
				</tr>
			</tbody>
		</table>
		<button>회원등록</button>
	</form>
</body>
</html>