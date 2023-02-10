package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class AddMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String savePath = req.getServletContext().getRealPath("/upload");
		int maxSize = (1024 * 1024 * 10);
		String encoding = "utf-8";

		MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());

		String id = multi.getParameter("id");
		String name = multi.getParameter("name");
		String phone = multi.getParameter("phone");
		String addr = multi.getParameter("addr");
		String img = multi.getParameter("image");
		String fileName = "";
		
		Enumeration<?> files = multi.getFileNames(); // 사진 여러장 업로드 가능
		while (files.hasMoreElements()) {
			String file = (String) files.nextElement(); //파일의 이름만 읽어들임
			System.out.println(file);
			fileName = multi.getFilesystemName(file); // 동일한 파일명이 있다면 바뀐파일의 이름을 읽어오는 것
		}
		
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(id);
		member.setMemberName(name);
		member.setMemberPhone(phone);
		member.setMemberAddr(addr);
		member.setImage(fileName);
		
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("member", member);
		
		Gson gson = new GsonBuilder().create();
		
		MemberService service = new MemberServiceMybatis();
		
		// {"retCode": "Success"}, {"retCode": "Fail"}
		if(service.addMember(member) > 0) {
			resultMap.put("retCode", "Success");
			//return "{\"retCode\": \"Success\"}.json";
		} else {
			resultMap.put("retCode", "Fail");
			//return "{\"retCode\": \"Fail\"}.json";
		}
		return gson.toJson(resultMap) + ".json"; // {"id":"user", "name":"user"...}
	}

}
