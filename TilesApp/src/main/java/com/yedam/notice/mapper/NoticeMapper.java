package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;

public interface NoticeMapper {
	
	public List<NoticeVO> selectList(); // 목록조회
	public NoticeVO searchOne(int nid);	// 단건조회
	public int insertNotice(NoticeVO notice); 
	public int updateNotice(NoticeVO notice);
	public int deleteNotice(int nid);
	public int increaseCnt(int nid); //조회수 증가
	// 댓글 목록
	// 댓글 등록
}