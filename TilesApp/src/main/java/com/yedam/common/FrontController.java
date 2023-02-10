package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.member.command.AddMember;
import com.yedam.member.command.ImageUpload;
import com.yedam.member.command.Login;
import com.yedam.member.command.LoginForm;
import com.yedam.member.command.ModifyMember;
import com.yedam.member.command.MyPageForm;
import com.yedam.member.command.RemoveMember;
import com.yedam.member.command.Logout;
import com.yedam.member.command.MemberList;
import com.yedam.member.command.MemberManager;
import com.yedam.notice.command.AddReply;
import com.yedam.notice.command.NoticeAdd;
import com.yedam.notice.command.NoticeDetail;
import com.yedam.notice.command.NoticeForm;
import com.yedam.notice.command.NoticeList;
import com.yedam.notice.command.RemoveReply;
import com.yedam.notice.command.ReplyList;

public class FrontController extends HttpServlet {

	// 필드
	private Map<String, Command> map; // Map<key, value>
	private String charset;

	// 생성자
	public FrontController() { // 한번만 실행
		map = new HashMap<String, Command>();
	}

	// 메소드
	@Override
	public void init(ServletConfig config) throws ServletException {

		charset = config.getInitParameter("charset"); // utf-8설정할때 필요해서 charset에 저장 해놓음

		map.put("/main.do", new MainControl()); // map.put => map에 넣는다(put) (key->("/main.do"), value->(new
												// MainControl()))
		map.put("/second.do", new SecondControl()); // map.put => map에 넣는다(put) (key->("/second.do"), value->(new
													// SecondControl()))

		// 공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeDetail.do", new NoticeDetail());
		map.put("/noticeForm.do", new NoticeForm()); // 글 등록 화면
		map.put("/noticeAdd.do", new NoticeAdd()); // 글 등록 처리

		// 댓글
		map.put("/replyList.do", new ReplyList()); // 댓글목록
		map.put("/removeReply.do", new RemoveReply()); // 댓글 삭제
		map.put("/addReply.do", new AddReply()); // 댓글 등록

		// 회원관련
		map.put("/loginForm.do", new LoginForm()); // 로그인 화면
		map.put("/login.do", new Login()); // 로그인 처리
		map.put("/logout.do", new Logout()); // 로그아웃
		map.put("/modifyMember.do", new ModifyMember()); // 회원정보 수정
		map.put("/myPageForm.do", new MyPageForm()); // 회원정보 수정 페이지
		map.put("/imageUpload.do", new ImageUpload()); // 이미지 변경 페이지
		
		// 관리자 회원관리
		map.put("/memberManageForm.do", new MemberManager()); //회원 정보페이지
		map.put("/memberList.do", new MemberList()); //회원 목록
		map.put("/addMember.do", new AddMember()); // 회원 등록
		//map.put("/modi", null)
		map.put("/removeMember.do", new RemoveMember()); // 회원 삭제
	}

	// req -> 클라이언트가 보내는 요청정보들이 담겨있음 ex) form이면 form안에 input의 value(입력)값
	// resp -> 요청한 정보들의 결과 값이 담겨있음

	@Override
	// 받은 예외를 톰캣이 웹브라우저에 출력
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);

		// /main.do를 알기위해 실행하는 코드 (규칙느낌) 그냥 외우기
		String uri = req.getRequestURI(); // uri = http://localhost:8080/TilesApp/
		String context = req.getContextPath(); // context = http://localhost:8080 -> 길이
		String page = uri.substring(context.length()); // substring = context길이 만큼 잘라냄, page => /TilesApp 저장
		System.out.println(page);

		// map.get(page) => page를 읽어와서 value를 실행 ex)NoticeDetail()이 실행
		Command command = map.get(page); // command = new NoticeDetail();
		String viewPage = command.exec(req, resp); // viewPage = main/main.titles가 저장

		if (viewPage.endsWith(".tiles")) { // ex)NoticeDetail에 return값이 .tails면
			RequestDispatcher rd = req.getRequestDispatcher(viewPage); // viewPage에 저장된 경로로 이동
			rd.forward(req, resp);
		} else if (viewPage.endsWith(".do")) {	// viewPage가 .do로 끝나면
			resp.sendRedirect(viewPage);
		} else if (viewPage.endsWith(".json")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().print(viewPage.substring(0, viewPage.length() - 5));
		}
	}
}
