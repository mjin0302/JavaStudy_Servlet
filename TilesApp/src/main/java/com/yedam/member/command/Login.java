package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class Login implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 성공하면 myPage로 이동하고
		// 로그인 실패하면 다시 로인화면으로 이동할때 "아이디와 패스워드를 확인" 하도록
		// 로그인 하면 로그아웃 로그아웃 하면 로그인(로그인한 유저 id 뜨게하기)
		// 로그인하면 마이페이지 수정 가능 하도록
		// 로그인 하면 게시글 등록목록 나오게 
		// 게시글 등록에서 작성자폼에 유저id 나오게 하기
		// 댓글에도 로그인한 유저 아이디
		// 댓글 목록에서 로그인 한 아이디가 쓴 글만 삭제 버튼 뜨게 하기
		
		// getParameter() => url주소에서 ?id=user1 user1의 값을 읽어올때 사용
		String id = req.getParameter("mid");
		String pass = req.getParameter("mpw");
		
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(pass);
		
		// MemberService를 오버라이딩 해서 MemberServiceMybatis
		MemberService service = new MemberServiceMybatis(); 
		
		MemberVO rvo = service.login(member);
		if(rvo != null) {
			req.setAttribute("vo", rvo);
			return "member/mypage.tiles";
		} else {
			System.out.println("LOGIN FALSE");
			req.setAttribute("result", "로그인 정보 다시 확인");
			return "loginForm.do";
		}
		
	}

}
