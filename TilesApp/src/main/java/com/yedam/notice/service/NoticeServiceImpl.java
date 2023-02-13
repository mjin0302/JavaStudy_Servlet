package com.yedam.notice.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.notice.mapper.NoticeMapper;
import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.ReplyVO;

public class NoticeServiceImpl implements NoticeService {

	SqlSession session = DataSource.getInstance().openSession(true); // 자동 커밋

	// session.selectOne("com.yedam.notice.mapper.NoticeMapper.getNotice")
	// NoticeService service = new NoticeServiceImpl();
	NoticeMapper mapper = session.getMapper(NoticeMapper.class); // mapper.xml과 mapper.java(인터페이스)를 매핑함

	// 목록 조회
	@Override
	public List<NoticeVO> noticeList() { // List<NoticeVO> => DB에서 여러건을 받아와서 넘겨줘야해서 리타입이 List<NoticeVO>임
		
		return mapper.selectList(); 
	}

	// 단건 조회
	@Override
	public NoticeVO getNotice(int nid) { // 한건만 조회하면 되는거라서 리턴타입이 NoticeVO
		mapper.increaseCnt(nid); //조회할때마다 카운트가 증가됨
		return mapper.searchOne(nid); 
	}

	// 등록
	@Override
	public int addNotice(NoticeVO notice) { // 등록하는 정보들 값을 읽어서 DB로 전송해주면 되는거라서 몇건이 등록만 넘겨주면되서 리턴타입 int
		
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
	public int removeNotice(int nid) {
		// TODO Auto-generated method stub
		return mapper.deleteNotice(nid);
	}

	@Override
	public List<ReplyVO> replyList(int nid) {
		
		return mapper.replyList(nid);
	}

	@Override
	public int removeReply(int rid) {
		// TODO Auto-generated method stub
		return mapper.deleteReply(rid);
	}
	
	@Override
	public int addReply(ReplyVO reply) {
		return mapper.insertReply(reply);
	}

}
