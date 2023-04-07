package com.DevFox.biz.comment;

import java.sql.Date;

public class CommentVO {
	
    private int commentNo;
    private int boardNo;
    private String writer;
    private String content;
    private String password;
    private Date regDate;
    private int like;
    private int bad;
    
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
	public int getCommentNo() {
		return commentNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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

	@Override
	public String toString() {
		return "CommentVO [commentNo=" + commentNo + ", boardNo=" + boardNo + ", writer=" + writer + ", content="
				+ content + ", regDate=" + regDate + "]";
	}
	
	
}