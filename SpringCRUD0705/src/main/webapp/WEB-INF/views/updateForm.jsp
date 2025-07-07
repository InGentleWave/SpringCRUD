<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<form action="/update.do" id="form" method="post" onsubmit="return fsubmit(this)">
		<input type="hidden" value="${member.memId }"/>
		<table class="table table-bordered">
			<tbody>
				<tr>
					<th>아이디</th><td>${member.memId }</td>
				</tr>
				<tr>
					<th>비밀번호</th><td><input type="text" name="memPw" id="memPw"></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th><td><input type="text" name="" id="pwChk" ></td>
				</tr>
				<tr>
					<th>이름</th><td><input type="text" name="memName" id="memName" value="${member.memName }"required></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>
						<input type="hidden" name="memBirthDate" id="memBirthDate">
						<c:choose>
							<c:when test="${fn:length(member.memBirthDate) eq '8' }">
								<c:set var="birth1" value="${fn:substring(member.memBirthDate,0,4) }"/>
								<c:set var="birth2" value="${fn:substring(member.memBirthDate,4,6) }"/>
								<c:set var="birth3" value="${fn:substring(member.memBirthDate,6,8) }"/>
							</c:when>
							<c:otherwise>
								<c:set var="birth1" value="${fn:substring(member.memBirthDate,0,2) }"/>
								<c:set var="birth2" value="${fn:substring(member.memBirthDate,2,4) }"/>
								<c:set var="birth3" value="${fn:substring(member.memBirthDate,4,6) }"/>
							</c:otherwise>
						</c:choose>
						<input type="text" name="" id="birth1" required value="${birth1}">
						
						<select id="birth2" required>
							<option value="01" <c:if test="${birth2 eq 01 }">selected</c:if>>1월</option>
							<option value="02" <c:if test="${birth2 eq '02' }">selected</c:if>>2월</option>
							<option value="03" <c:if test="${birth2 eq '03' }">selected</c:if>>3월</option>
							<option value="04" <c:if test="${birth2 eq 04 }">selected</c:if>>4월</option>
							<option value="05" <c:if test="${birth2 eq '05' }">selected</c:if>>5월</option>
							<option value="06" <c:if test="${birth2 eq '06' }">selected</c:if>>6월</option>
							<option value="07" <c:if test="${birth2 eq '07' }">selected</c:if>>7월</option>
							<option value="08" <c:if test="${birth2 eq '08' }">selected</c:if>>8월</option>
							<option value="09" <c:if test="${birth2 eq '09' }">selected</c:if>>9월</option>
							<option value="10" <c:if test="${birth2 eq '10' }">selected</c:if>>10월</option>
							<option value="11" <c:if test="${birth2 eq '11' }">selected</c:if>>11월</option>
							<option value="12" <c:if test="${birth2 eq '12' }">selected</c:if>>12월</option>
						</select>
						<input type="text" name="" id="birth3" required value=${birth3 }>
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="memGender" id="" value="male" <c:if test="${member.memGender eq 'male' }">checked</c:if>> 남성
						<input type="radio" name="memGender" id="" value="female" <c:if test="${member.memGender eq 'female' }">checked</c:if>> 여성
					</td>
				</tr>
				<tr>
					<th>이메일</th><td><input type="text" name="memEmail" id="" value="${member.memEmail }"></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>
						<select name="memNation" required>
							<option value="">국가 선택</option>
							<option value="대한민국 +082" <c:if test="${member.memNation eq '대한민국 +082' }">selected</c:if>>대한민국 +082</option>
						</select>
						<input type="text" name="memPhone" id="" required value="${member.memPhone }">
					</td>
				</tr>
			</tbody>
		</table>
		<div>
			<button type="submit" id="updateBtn">회원정보 수정</button>
			<button id="cancleBtn">취소</button>
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
		let updateBtn = $("#updateBtn");
		let cancleBtn = $("#cancleBtn");

		

		cancleBtn.on("click",function(){
			location.href="/detail.do?memId=${member.memId}";
		})
	})

</script>
</html>