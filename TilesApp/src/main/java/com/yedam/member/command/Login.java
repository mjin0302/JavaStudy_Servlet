package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;

public class Login implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 성공하면 myPage로 이동하고
		// 로그인 실패하면 다시 로인화면으로 이동할때 "아이디와 패스워드를 확인" 하도록
		return "";
	}

}
