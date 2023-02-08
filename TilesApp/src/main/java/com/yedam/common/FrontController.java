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

import com.yedam.notice.command.*;

public class FrontController extends HttpServlet {

	// 필드
	private Map<String, Command> map;
	private String charset;

	// 생성자
	public FrontController() { // 한번만 실행
		map = new HashMap<String, Command>();
	}

	// 메소드
	@Override
	public void init(ServletConfig config) throws ServletException {

		charset = config.getInitParameter("charset"); // utf-8설정할때 필요해서 charset에 저장 해놓음

		map.put("/main.do", new MainControl());
		map.put("/second.do", new SecondControl());

		// 공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeDetail.do", new NoticeDetail());
		map.put("/noticeForm.do", new NoticeForm());
		map.put("/noticeAdd.do", new NoticeAdd());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);

		// /main.do를 알기위해 실행하는 코드 (규칙느낌) 그냥 외우기
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String page = uri.substring(context.length());
		System.out.println(page);

		Command command = map.get(page);
		String viewPage = command.exec(req, resp); // viewPage = main/main.titles가 저장

		if (viewPage.endsWith(".tiles")) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage); // viewPage에 저장된 경로로 이동
			rd.forward(req, resp);
		} else if (viewPage.endsWith(".do")) {
			resp.sendRedirect(viewPage);
		}

	}
}
