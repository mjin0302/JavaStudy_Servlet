package com.yedam.member.service;

import java.util.List;

import com.yedam.member.vo.MemberVO;

public interface MemberService {
	// mapper id 값이랑 (login) 같아야함
	// 추상 메소드
	public MemberVO login(MemberVO member);

	public int addMember(MemberVO member);

	public List<MemberVO> memberList();

	public MemberVO getMember(String id); // 회원정보 조회용

	public int modifyMember(MemberVO member); // 회원정보 수정
	
	public int removeMember(String mid); // 회원정보 삭제
}
