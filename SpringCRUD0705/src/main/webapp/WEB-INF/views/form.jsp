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
	<form action="/insert.do" id="form" method="post" onsubmit="return fsubmit(this)">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th>아이디</th><td><input type="text" name="memId" id="memId" required></td>
				</tr>
				<tr>
					<th>비밀번호</th><td><input type="text" name="memPw" id="memPw" required></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th><td><input type="text" name="" id="pwChk" required></td>
				</tr>
				<tr>
					<th>이름</th><td><input type="text" name="memName" id="memName" required></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="hidden" name="memBirthDate" id="memBirthDate">
						<input type="text" name="" id="birth1" required>
						<select id="birth2" required>
							<option value="01">1월</option>
							<option value="02">2월</option>
							<option value="03">3월</option>
							<option value="04">4월</option>
							<option value="05">5월</option>
							<option value="06">6월</option>
							<option value="07">7월</option>
							<option value="08">8월</option>
							<option value="09">9월</option>
							<option value="10">10월</option>
							<option value="11">11월</option>
							<option value="12">12월</option>
						</select>
						<input type="text" name="" id="birth3" required>
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="memGender" id="" value="male" checked> 남성
						<input type="radio" name="memGender" id="" value="female"> 여성
					</td>
				</tr>
				<tr>
					<th>이메일</th><td><input type="text" name="memEmail" id=""></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<select name="memNation" required>
							<option value="대한민국 +082">대한민국 +082</option>
						</select>
						<input type="text" name="memPhone" id="" required>
					</td>
				</tr>
			</tbody>
		</table>
		<div>
			<button type="submit" id="addBtn">회원등록</button>
			<button id="listBtn">목록</button>
		</div>
	</form>
</body>
<script>
	function fsubmit(){
			// memBirthDate 조합
			let memBirthDate = $("#memBirthDate");
			let birth1 = $("#birth1").val();
			let birth2 = $("#birth2").val();
			let birth3 = $("#birth3").val();
			memBirthDate.val(birth1+birth2+birth3);

			// 유효성 검사

			// 폼 제출
// 			console.log(memBirthDate.val());
			return true;
		}
	$(function(){
		let addBtn = $("#addBtn");
		let listBtn = $("#listBtn");

		

		listBtn.on("click",function(){
			location.href="/list.do";
		})
	})

</script>
</html>