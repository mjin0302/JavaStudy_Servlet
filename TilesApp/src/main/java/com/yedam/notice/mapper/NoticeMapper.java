package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.ReplyVO;

public interface NoticeMapper {

	public List<NoticeVO> selectList(); // 목록조회

	public NoticeVO searchOne(int nid); // 단건조회

	public int insertNotice(NoticeVO notice); // 글 등록

	public int updateNotice(NoticeVO notice); // 글 수정

	public int deleteNotice(int nid); // 글 삭제

	public int increaseCnt(int nid); // 조회수 증가
	// 댓글 목록

	public List<ReplyVO> replyList(int nid); // 원본 글 번호를 매개값으로 받아와서 list에 담는다

	public int insertReply(ReplyVO reply); // 댓글 등록

	public int deleteReply(int rid); // 댓글 삭제 // 매개값 : 댓글 번호
}
