package com.yedam.notice.vo;

import java.util.Date;

import lombok.Data;

// 값을 저장하는 클래스
@Data
public class NoticeVO {

	// 필드
	private int noticeId;
	private String noticeWriter;
	private String noticeTitle;
	private String noticeSubject;
	private Date noticeDate;
	private int hitCount;
	private String attachFile;
}
