package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.ReplyVO;

public interface NoticeService {

	public List<NoticeVO> noticeList();

	public NoticeVO getNotice(int nid); // 한건 조회

	public int addNotice(NoticeVO notice); // 글 등록

	public int modNotice(NoticeVO notice); // 글 수정

	public int removeNotice(int nid); // 글 삭제

	int addReply(ReplyVO reply); // 댓글 등록

	public List<ReplyVO> replyList(int nid); // 댓글 목록

	// 댓글 삭제
	// 댓글 번호
	public int removeReply(int rid);

}
