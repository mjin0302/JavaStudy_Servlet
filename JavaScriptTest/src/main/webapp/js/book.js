/**
 * 
 */

// bookList배열을 활용해서 처리하도록 하세요.
let bookList = [
	{bookCode: 'P0206001', bookTitle: '이것이자바다', bookAuthor: '홍성문', bookPress: '신흥출판사', bookPrice: '20000'},
	{bookCode: 'P0206002', bookTitle: '이것이자바스크립트다', bookAuthor: '홍영웅', bookPress: '우리출판사', bookPrice: '25000'},
]

makeTag();

function makeTag() {

	// 목록조회
	bookList.forEach(function(book) {
		let tr = document.createElement("tr");

		//체크박스
		let td = document.createElement("td");
		let input = document.createElement("input");
		input.setAttribute("type", "checkbox");
		td.append(input);
		tr.append(td);

		//bookCode
		td = document.createElement("td");
		td.innerText = book.bookCode;
		tr.append(td);

		//bookTitle
		td = document.createElement("td");
		td.innerText = book.bookTitle;
		tr.append(td);

		//bookAuthor
		td = document.createElement("td");
		td.innerText = book.bookAuthor;
		tr.append(td);

		//bookPress
		td = document.createElement("td");
		td.innerText = book.bookPress;
		tr.append(td);

		//bookPrice
		td = document.createElement("td");
		td.innerText = book.bookPrice;
		tr.append(td);
		
		//삭제 버튼
		td = document.createElement("td");
		let button = document.createElement("button");
		button.innerText = "삭제"
		td.append(button);
		tr.append(td);

		list.append(tr);
	});
}

function insertFnc(evnt) {
	
}