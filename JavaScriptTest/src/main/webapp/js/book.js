/**
 *
 */

// bookList배열을 활용해서 처리하도록 하세요.
let bookList = [
  {
    bookCode: "P0206001",
    bookTitle: "이것이자바다",
    bookAuthor: "홍성문",
    bookPress: "신흥출판사",
    bookPrice: "20000",
  },
  {
    bookCode: "P0206002",
    bookTitle: "이것이자바스크립트다",
    bookAuthor: "홍영웅",
    bookPress: "우리출판사",
    bookPrice: "25000",
  },
];

makeTag();

function makeTag() {
  list.innerHTML = "";
  // 목록조회
  bookList.forEach(function (book) {
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
    button.innerText = "삭제";
    td.append(button);
    tr.append(td);

    list.append(tr);
  });
}

addBtn.addEventListener("click", insertListFnc);
function insertListFnc() {
  let book = {
    bookCode: bookCode.value,
    bookTitle: bookName.value,
    bookAuthor: author.value,
    bookPress: press.value,
    bookPrice: price.value,
  };

  bookList.push(book);
  console.log(bookList);
  makeTag();

  bookCode.value = "";
  bookName.value = "";
  author.value = "";
  press.value = "";
  price.value = "";
}

list.addEventListener("click", deleteListFnc);
function deleteListFnc(ev) {
  if (ev.target.nodeName == "BUTTON") {
    let re = ev.target.closest("tr").children[1];
    let delIndex = bookList.findIndex((e) => e.bookCode == re.innerText);
    bookList.splice(delIndex, 1);
    makeTag();
  }
}

// 1. title아래 input을 체크박스 찾기

// 2. checkbox=true면 누르면 list아래에 있는 체크 박스들의 속성이 true
check.addEventListener("click", checkBoxFnc);
function checkBoxFnc() {
  document.querySelectorAll("#list input[type='checkbox']").forEach((chk) => {
    //체크 박스가 checked(boolean)
    if (check.checked == true) {
      chk.checked = this.checked;
      console.log(this);
    } else {
      chk.checked = this.checked;
    }
  });
}

// 선택 삭제를 누르면 선택된 데이터 삭제 실행(이벤트 실행)
// list안에 checked가된 체크박스를 가져옴
checkDel.addEventListener("click", checkBoxDelFnc);
function checkBoxDelFnc() {
  document
    .querySelectorAll("#list input[type='checkbox']:checked")
    .forEach((chk) => {
      let re = chk.closest("tr").children[1];
      let delIndex = bookList.findIndex((e) => e.bookCode == re.innerText);
      bookList.splice(delIndex, 1);
      makeTag();
    });
}
