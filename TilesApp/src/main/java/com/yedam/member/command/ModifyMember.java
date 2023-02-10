package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class ModifyMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 아이디 이름 비밀번호 연락처 주소
		String id = req.getParameter("mid");
		String name = req.getParameter("mname");
		String pass = req.getParameter("mpass");
		String phone = req.getParameter("mphone");
		String addr = "";
		
		
		if(req.getParameter("maddr").equals("")) {
			addr = "";
		} else {
			addr = req.getParameter("maddr");
		}

		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberName(name);
		member.setMemberPw(pass);
		member.setMemberPhone(phone);
		member.setMemberAddr(addr);
		
		System.out.println("addr" + addr);
		
		
		MemberService service = new MemberServiceMybatis();

		MemberVO mvo = service.getMember(id);
		System.out.println("mvo" + mvo.getMemberAddr());
		if (name.equals(mvo.getMemberName()) && pass.equals(mvo.getMemberPw()) && phone.equals(mvo.getMemberPhone())
				&& addr.equals(mvo.getMemberAddr())) {
			System.out.println("수정된 값이 없습니다");
			
			return "member/mypage.tiles";
		} else {
			service.modifyMember(member);
			return "noticeList.do";
		}
	}

}
