<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
    
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>

    <div>
        작성자 : <input type="text" id="writer" value="user1" readonly>
        제목 : <input type="text" id="title">
        내용 : <input type="text" id="subject">
        <button id="addBtn">저장</button>
        <button id="delBtn">삭제</button>
    </div>
    <br/>

    <table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>글번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>조회수</th>
                <th>작성일자</th>
            </tr>
        </thead>
        <tbody>
	        <c:forEach var="notice" items="${ noticeList }">
	        	<tr>
	                <td>${ notice.noticeId }</td>
	                <td>${ notice.noticeWriter }</td>
	                <td>${ notice.noticeTitle }</td>
	                <td>${ notice.hitCount }></td>
	                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${ notice.noticeDate }"/></td>
	            </tr>
	        </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <th>글번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>조회수</th>
                <th>작성일자</th>
            </tr>
        </tfoot>
    </table>
    <script>
        var t = $('#example').DataTable();

            $('#addBtn').on('click', function () {

                var formData = new FormData();

                formData.append("writer", $("#writer").val());
                formData.append("title", $("#title").val());
                formData.append("subject", $("#subject").val());
                // db insert후 화면처리
                $.ajax({
                    url: "noticeAddJson.do",
                    method: "post",
                    data: formData,
                    contentType: false,
                    processData: false,
                    success:  function (result) {
                        console.log(result);
                        t.row.add([result.noticeId, result.noticeWriter, result.noticeTitle, result.hitCount, result.noticeDate]).draw(false);
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            });

            // Automatically add a first row of data
            //$('#addRow').click()
            
            //tr선택하면 스타일 변경
            $('#example tbody').on('click', 'tr', function () {
		        if ($(this).hasClass('selected')) {
		            $(this).removeClass('selected');
		        } else {
		            t.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
	        }
		    console.log($(this).children().eq(0).text())
            // 클릭할때마다 localStorage에 noticeId라는 이름으로 값을 저장 -> 값들이 덮어씌어짐

            localStorage.setItem("noticeId", $(this).children().eq(0).text())
    });

    // 삭제버튼
    $("#delBtn").on("click", function() {
        // noticeId에 저장된 걸 읽어 오겠다
        console.log("삭제할 글 번호 : " + localStorage.getItem("noticeId"));
        console.log("삭제할 글 번호2 : " + localStorage.getItem("noticeId"));
        t.row(".selected").remove().draw(false);

        // db delete후 화면처리
        $.ajax({
            url: "noticeAddJson.do",
            method: "post",
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                console.log(result);
                t.row.add([result.noticeId, result.noticeWriter, result.noticeTitle, result.hitCount, result.noticeDate]).draw(false);
            },
            error: function (error) {
                console.log(error);
            }
        });
    })
    </script>