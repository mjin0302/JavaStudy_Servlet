<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<h3>현재 페이지는 memberList.do의 결과 memberList.jsp 입니다.</h3>
<h3>회원목록</h3>

<table class="table">
	<thead>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Phone</th>
			<th>Address</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="member" items="${ memberList }">
			<tr>
				<td>${ member.memberId }</td>
				<td>${ member.memberName }</td>
				<td>${ member.memberPhone }</td>
				<td>${ member.memberAddr }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<jsp:include page="../includes/footer.jsp"></jsp:include>