package com.yedam.notice.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.notice.mapper.NoticeMapper;
import com.yedam.notice.vo.NoticeVO;

public class NoticeServiceImpl implements NoticeService {

	SqlSession session = DataSource.getInstance().openSession(true); // 자동 커밋

	// session.selectOne("com.yedam.notice.mapper.NoticeMapper.getNotice")
	// NoticeService service = new NoticeServiceImpl();
	NoticeMapper mapper = session.getMapper(NoticeMapper.class); // mapper.xml과 mapper.java(인터페이스)를 매핑함

	// 목록 조회
	@Override
	public List<NoticeVO> noticeList() {
		
		return mapper.selectList();
	}

	// 단건 조회
	@Override
	public NoticeVO getNotice(int nid) {
		mapper.increaseCnt(nid); //조회할때마다 카운트가 증가됨
		return mapper.searchOne(nid);
	}

	// 등록
	@Override
	public int addNotice(NoticeVO notice) {
		// TODO Auto-generated method stub
		return mapper.insertNotice(notice);
	}

	// 수정
	@Override
	public int modNotice(NoticeVO notice) {
		// TODO Auto-generated method stub
		return mapper.updateNotice(notice);
	}

	// 삭제
	@Override
	public int remNotice(int nid) {
		// TODO Auto-generated method stub
		return mapper.deleteNotice(nid);
	}

}
