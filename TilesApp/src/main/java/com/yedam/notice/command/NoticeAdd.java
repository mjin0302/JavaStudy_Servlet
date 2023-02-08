package com.yedam.notice.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeAdd implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 글 등록 처리
		String savePath = req.getServletContext().getRealPath("/upload");
		int maxSize = (1024 * 1024 * 10);
		String encoding = "utf-8";

		try {
			// 파일 업로드 서블릿
			MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());

			String title = multi.getParameter("title");
			String subject = multi.getParameter("subject");
			String writer = multi.getParameter("writer");
			String fileName = "";

			Enumeration<?> files = multi.getFileNames(); // 사진 여러장 업로드 가능
			while (files.hasMoreElements()) {
				String file = (String) files.nextElement();
				System.out.println(file);
				fileName = multi.getFilesystemName(file);
			}
			// NoticeVO생성
			NoticeVO vo = new NoticeVO();
			vo.setAttachFile(fileName);
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			vo.setNoticeWriter(writer);

			NoticeService service = new NoticeServiceImpl();
			service.addNotice(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "noticeList.do";

	}

}
