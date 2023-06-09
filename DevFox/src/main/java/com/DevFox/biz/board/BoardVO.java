package com.DevFox.biz.board;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;


	private int type;
	private int cnt;
	private String searchCondition;
	private String searchKeyword;
	private MultipartFile uploadFile;
	private String password;
	private int like;
	private int bad;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}


	
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
	
	@Override
	public String toString() {
		
		return "BoardVO [seq="+seq+", title="+title+", writer="+writer+", content="+content+", regDate="+regDate+", cnt="+cnt+", type="+type+"]";
	}
	
}
