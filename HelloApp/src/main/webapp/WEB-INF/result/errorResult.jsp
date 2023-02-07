<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result/errorResult</title>
</head>
<body>
	<h3>처리중 에러가 발생!</h3>

	<!-- set : 변수에 값을 담아주고싶을때 사용 -->
	<!-- c : 태그를 의미 -->
	<!--  % String name = "홍길동" % 과 같은 의미임 -->
	<!--  var라는 속성에다가 value를 집어넣음 -->
	<c:set var="myname" value="홍길동"></c:set>
	<!--  c:out 은 값을 불러옴(출력) -->
	<c:out value="${myname}"></c:out>
	<!-- 이런식으로 값을줘도됨 -->
	<p>${myname }</p>


	<!-- if문 예제 -->
	<c:set var="score" value="75"></c:set>
	<c:if test="${score > 60}">
		<p>합격!</p>
	</c:if>
	<c:if test="${score < 60}">
		<p>불합격!</p>
	</c:if>


	<!-- if else문 예제 -->
	<!-- c:otherwise는 else문을 의미함 -->
	<c:choose>
		<c:when test="${score > 90}">
			<p>A학점</p>
		</c:when>
		<c:when test="${score > 80}">
			<p>B학점</p>
		</c:when>
		<c:when test="${score > 70}">
			<p>C학점</p>
		</c:when>
		<c:otherwise>
			<p>D학점</p>
		</c:otherwise>
	</c:choose>


	<!-- forEach 예제 -->
	<c:forEach begin="1" end="10" var="num">
		<p class='<c:out value="${num}"/>'>${num}</p>
		<!-- class라는 attribute에다가도 넣어줄수있음 -->
	</c:forEach>

	<p>3단 출력하기</p>
	<c:set var="dan" value="4"></c:set>
	<table border="1">
		<c:forEach begin="1" end="9" var="i">
			<tr>
				<td>${dan }</td>
				<td>*</td>
				<td>${i }</td>
				<td>= ${dan*i }</td>
			</tr>
		</c:forEach>
	</table>

	<c:forEach items="${empList }" var="emp">
		<p>사번: ${emp.employeeId }, 이름: ${emp.firstName }, 이메일: ${emp.email },
			직무: ${emp.jobId }</p>
	</c:forEach>

	<h3>Expression Language (EL)</h3>
	<p>${3>5 }</p>
	<p>${'test'=='test' }</p>
	<p>${5-4 }</p>
	<p>${5*4 }</p>
	<p>${id }EL안에서${id }=>getAttribute()</p>
	<p>${id eq null }</p>
	<p>${empty id}</p>
	<p>${obj }</p>
	<p>ID: ${obj.memberId }</p>
	<p>Name: ${obj.memberName }</p>
	<p>List: ${empList }</p>

	<p>html안에 자바코드 제거 => jstl 연습.</p>
</body>
</html>