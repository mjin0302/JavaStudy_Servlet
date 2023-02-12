console.log("manage.js start...")

// makeRow() tag생성
function makeRow(member = {}) {
   // member값을 활용해서 tr 생성
   let tr = $('<tr/>'); // =document.createElement("tr")
   tr.on("dblclick", function(e) {
      let id = $(this).children().eq(0).text(); // 가지고온 children중 첫번째 값
      let name = $(this).children().eq(1).text();
      let phone = $(this).children().eq(2).text();
      let addr = $(this).children().eq(3).text();
      let resp = $(this).children().eq(4).text();

      let nTr = $('<tr/>').append(
         $('<td />').text(id),
         $('<td/>').append($('<input class="name"/>').val(name)),
         $('<td/>').append($('<input class="phone"/>').val(phone)),
         $('<td/>').append($('<input class="addr"/>').val(addr)),
         $('<td/>').append($('<input class="resp"/>').val(resp)),
         $('<td/>').append($('<button onclick="updateMemberFnc(event)">수정</button>'))
      )
      // console.log(nTr);
      // nTr = $("#template tr").clone(true);
      // nTr.find("input.name").val(name);
      // // 새로운 tr로 기존 tr을 대신
      $(this).replaceWith(nTr);
   }) //end of dblclick

   tr.append(
      $('<td/>').text(member.memberId),  //.text() = innerText
      $('<td/>').text(member.memberName),
      $('<td/>').text(member.memberPhone),
      $('<td/>').text(member.memberAddr),
      $('<td/>').text(member.responsibility),
      $('<td/>').append(
                  // .attr("mid") = setAttribute
                  $('<button>삭제</button>').attr("mid", member.memberId).on("click", delMemberFnc)
         )
   );
   return tr;
} // end of makeRow()

// updateMemberFnc()
function updateMemberFnc(e) {
   // modifyMember.do 사용자 정보 수정
   let tr = $(e.target).parent().parent(); //tr
   
   //console.log(tr.find("td:nth-of-type(2) input").val()); //input value값을 읽어오는거
   console.log(tr.find("input.name").val()); //tr하위 요소중 input태그를 찾아서 input class="name"인걸 찾아오겠다
   console.log(tr.find("input.phone").val()); //tr하위 요소중 input태그를 찾아서 input class="phone"인걸 찾아오겠다
   console.log(tr.find("input.addr").val()); //tr하위 요소중 input태그를 찾아서 input class="addr"인걸 찾아오겠다
   console.log(tr.find("input.resp").val()); //tr하위 요소중 input태그를 찾아서 input class="resp"인걸 찾아오겠다

   let id = tr.children().eq(0).text();
   let name = tr.find("input.name").val(); 
   let phone = tr.find("input.phone").val(); 
   let addr = tr.find("input.addr").val(); 
   let resp = tr.find("input.resp").val();

   // get = select, post = insert, put = update 
	$.ajax({
		url:"modifyUpdate.do",
		method:"post",
		data: {id:id, name:name, phone:phone, addr:addr, resp:resp},
		success: function(result) {
         if(result.retCode == 'Success') {
            tr.replaceWith(makeRow(result.member));
         } else {
            alert("error")
         }
   },
		error: function(error) {
			console.log(error);
		}
	});
} // end of updateMemberFnc()

// delMemberFnc() => 회원삭제
function delMemberFnc(e) {
   if (!window.confirm('삭제하시겠습니까?')) {
      return;
   }

   let user = $(e.target).attr("mid");

   $.ajax({
      url: "removeMember.do",
      data: {id:user}, // removeMember.do?id=user
      success: function(result) {
         if (result.retCode == 'Success') {
            $(e.target).parent().parent().remove();
         } else {
            alert('삭제건수가 없습니다!')
         }
         //console.log(result)
         //$(e.target).parent().parent().remove();
      },
      error: function(error) {
         console.log(error)
         alert('처리코드: ' + reject.status + '\n' + //
            '에러메세지: ' + reject.statusText)
      }
   });
} // end of deleteFnc()

// 로딩했을 때 화면에 보여줌
// document(html,css) 읽어오고 -> 함수 실행
$(document).ready( function() {
   // let clone = $("#template").clone(true);
   // console.log(clone.find("tr"));
   // let tr = clone.find("tr");
   // tr.find(".name").val("test");

   // $("#list").append(tr);

   // 목록가져오는 Ajax 호출
   $.ajax({
      url: "memberList.do",
      success: function(result) {
         console.log(result);
         $(result).each(function(idx, item) { //$(result).each = result.for.each
            $("#list").append(makeRow(item));
         })
      },
      error: function(error) {
         console.log(error)
      }
   });

   // 등록 이벤트
   // addBtn.addEventListener("click", function())
   $("#addBtn").on("click", function() {
      let id = $("#mid").val(); // element.value속성을 읽어옴
      let name = $("#mname").val();
      let phone = $("#mphone").val();
      let addr = $("#maddr").val();
      let img = $("#mimage")[0].files[0];

      let formData = new FormData(); //FormData(key, value)
      formData.append("id", id);
      formData.append("name", name);
      formData.append("phone", phone);
      formData.append("addr", addr);
      formData.append("img", img);

      $.ajax({
         url: "addMember.do",
         method:"post",
         data: formData,
         contentType: false,
         processData: false,
         success: function(result) {
            // 서버에서 받아온 정보를 화면에 생성
            console.log(result);
            if(result.retCode == "Success") {
               $("#list").append(makeRow(result.member));
            } else {
               alert("입력 미완!")
            }
         },
         error: function(error) {
            console.log(error);
         }
      })
   })
});
