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
