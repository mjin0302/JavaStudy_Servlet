<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<h3>현재 페이지는 myPageForm.do의 결과 mypage.jsp 입니다.</h3>

<table class="table">
	<tr>
		<th>아이디</th>
		<td>
			<input type="text" name="mid" value="${vo.memberId }" readonly>
		</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>
			<input type="text" name="mname" value="${vo.memberName }">
		</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>
			<input type="text" name="mpass" value="${vo.memberPw }">
		</td>
	</tr>
	<tr>
		<th>연락처</th>
		<td>
			<input type="text" name="mphone" value="${vo.memberPhone }">
		</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>
			<input type="text" name="maddr" value="${vo.memberAddr }">
		</td>
	</tr>
	<tr>
		<th>images</th>
		<td>
			<img src="images/${vo.image }" width="150px" height="auto">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="수정">
		</td>
	</tr>
</table>
