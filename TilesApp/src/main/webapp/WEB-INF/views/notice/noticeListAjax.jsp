<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--     noticeListAjax.jsp=> return "notice/noticeListAjax.tiles" 
	     서버 : noticeListJson.do => json데이터 반환 -->

<link rel="stylesheet" href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>

<div>
    <button id="addBtn">저장</button>
    <button id="delBtn">삭제</button>
</div>
<table id="example" class="display" style="width:100%">
    <thead>
        <tr>
            <th>Num</th>
            <th>User Id</th>
            <th>Content</th>
            <th>Views</th>
            <th>Date</th>
        </tr>
    </thead>
    <tbody>

    </tbody>
    <tfoot>
        <tr>
            <th>Num</th>
            <th>User Id</th>
            <th>Content</th>
            <th>Views</th>
            <th>Date</th>
        </tr>
    </tfoot>
</table>

<script>
    var table = $('#example').DataTable({
        ajax: 'noticeListJson.do',
    });

    $('#example tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        console.log($(this).children().eq(0).text())
        // 클릭할때마다 localStorage에 noticeId라는 이름으로 값을 저장 -> 값들이 덮어씌어짐

        localStorage.setItem("noticeId", $(this).children().eq(0).text())
    });

    $('#delBtn').click(function () {
        table.row('.selected').remove().draw(false);

        $.ajax({
            url: "noticeRemove.do",
            method: "post",
            data: { id: localStorage.getItem("noticeId") },
            success: function (result) {
                if (result.retCode == "Success") {
                    $("tr[data-id=" + replyId + "]").remove();
                } else {
                    alert("처리 실패");
                }
            },
            error: function (reject) {
                console.log(reject)
            }
        })
    });
</script>