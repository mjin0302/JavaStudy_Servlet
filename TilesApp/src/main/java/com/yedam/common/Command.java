package com.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	// exec를 호출하면 => ServletException, IOException 예외로 넘겨줌
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
