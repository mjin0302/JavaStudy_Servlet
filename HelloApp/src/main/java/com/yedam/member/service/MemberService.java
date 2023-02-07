package com.yedam.member.service;

import java.util.List;

import com.yedam.member.vo.MemberVO;


public interface MemberService {
	//mapper id 값이랑 (login) 같아야함
	public MemberVO login(MemberVO member);
	public int addMember(MemberVO member);
	public List<MemberVO> memberList();
}
