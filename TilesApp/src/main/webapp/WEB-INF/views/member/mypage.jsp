<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<h3>현재 페이지는 myPageForm.do의 결과 mypage.jsp 입니다.</h3>
<form action="modifyMember.do?mid=${vo.memberId}" method="post">
	
	<input type="file" id="fileUpload" accept="image/*" style="display: none" onchange="imageChangeFnc()" />
	<table class="table">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="mid" value="${vo.memberId }" readonly /></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="mname" value="${vo.memberName }" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="mpass" value="${vo.memberPw }" /></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><input type="text" name="mphone" value="${vo.memberPhone }" /></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="maddr" value="${vo.memberAddr }" /></td>
		</tr>
		<tr>
			<th>image</th>
			<td><img id="imgSrc" width="150px" src="upload/${ img }" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="수정" /></td>
		</tr>
	</table>
</form>

<script>
	// event등록 : addEventListener("type", function(){})
	$("#imgSrc").on("click", function() {
		$("#fileUpload").click();
	});

	function imageChangeFnc() {
		console.log($("#fileUpload")[0].files[0]);
		let file = $("#fileUpload")[0].files[0] //서버 업로드 할때 필요한 정보

		let formData = new FormData(); // multipart요청 처리
		formData.append("id", "${vo.memberId }"); // id, file 업로드 : db변경
		formData.append("image", file);
		// 서버에 multipart/form-data : ajax 요청
		$.ajax({
			url : "imageUpload.do",
			method : "post",
			data : formData,
			contentType : false, // multipart요청일 경우에 옵션
			processData : false, // multipart요청일 경우에 옵션
			success : function(result) {
				console.log(result);
				// 화면에서도 선택된 이미지가 보여줌
				let reader = new FileReader(); //파일을 읽어드림
				reader.onload = function(ev) { // 파일을 읽어드릴때 onload
					console.log(ev.target)
					$("#imgSrc").attr("src", ev.target.result);
				}
				reader.readAsDataURL();
			},
			error : function(err) {
				console.log(err);
			}
		});
	}
</script>
