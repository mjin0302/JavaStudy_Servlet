package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class ImageUpload implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		String savePath = req.getServletContext().getRealPath("/upload");
		int maxSize = (1024 * 1024 * 10);
		String encoding = "utf-8";

		try {
			// 파일 업로드 서블릿
			MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());

			String id = multi.getParameter("id");
			String fileName = "";

			Enumeration<?> files = multi.getFileNames(); // 사진 여러장 업로드 가능
			while (files.hasMoreElements()) {
				String file = (String) files.nextElement(); //파일의 이름만 읽어들임
				System.out.println(file);
				fileName = multi.getFilesystemName(file); // 동일한 파일명이 있다면 바뀐파일의 이름을 읽어오는 것
			}
			
			HttpSession session = req.getSession();
	        session.setAttribute("img", fileName);
			
			MemberVO vo = new MemberVO();
			vo.setMemberId(id);
			vo.setImage(fileName);
			
			MemberService service = new MemberServiceMybatis();
			service.modifyMember(vo);
			
			System.out.println(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// {"retcode": "Success"}
		return "{\"retcode\": \"Success\"}";
	}

}
