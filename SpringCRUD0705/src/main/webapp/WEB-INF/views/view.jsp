<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<table class="table table-bordered">
		<tbody>
			<tr>
				<th>아이디</th>
				<td>${member.memId }</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>${member.memPw }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${member.memName }</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>${member.memBirthDate }</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
				<c:choose>
					<c:when test="${member.memGender eq 'male'}">
						남성
					</c:when>
					<c:otherwise>
						여성
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${member.memEmail }</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>
					(${member.memNation }) ${member.memPhone }
				</td>
			</tr>
		</tbody>
	</table>
	<div>
		<button type="button" id="updateBtn">수정</button>
		<button id="listBtn">목록</button>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		let updateBtn = $("#updateBtn");
		let listBtn = $("#listBtn");
		
		updateBtn.on("click",function(){
			location.href="/update.do?memId=${member.memId}";
		});
		listBtn.on("click",function(){
			location.href="/list.do";
		});
	})
</script>
</html>