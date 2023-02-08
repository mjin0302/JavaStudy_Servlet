package com.yedam.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.mapper.MemberMapper;
import com.yedam.member.vo.MemberVO;

//인터페이스를 구현하는 클래스
public class MemberServiceMybatis implements MemberService {
	// 자동 커밋
	SqlSession session = DataSource.getInstance().openSession(true);

	// session.getMapper(인터페이스타입.class)를 통해 메소드 호출 가능
	MemberMapper mapper = session.getMapper(MemberMapper.class);

	@Override
	public MemberVO login(MemberVO member) {
		
		return mapper.login(member); // session.selectOne("네임스페이스");
	}

	@Override
	public int addMember(MemberVO member) {
		// TODO Auto-generated method stub
		return mapper.addMember(member);
	}

	@Override
	public List<MemberVO> memberList() {
		return mapper.memberList();
	}

	@Override
	public MemberVO getMember(String id) {
		// TODO Auto-generated method stub
		return mapper.getMember(id);
	}
	

}
