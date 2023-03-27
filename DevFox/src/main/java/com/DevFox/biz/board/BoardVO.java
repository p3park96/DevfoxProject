

package com.DevFox.biz.board;

import java.util.Date;

public class BoardVO {
	  private int boardId;
	  private String title;
	  private String content;
	  private String writer;
	  private Date WriteDate;

	  public BoardVO(int boardId, String title, String content, String writer, Date WriteDate) {
	    this.boardId = boardId;
	    this.title = title;
	    this.content = content;
	    this.writer = writer;
	    this.WriteDate = WriteDate;
	  }

	  public int getBoardId() {
	    return boardId;
	  }

	  public void setBoardId(int boardId) {
	    this.boardId = boardId;
	  }

	  public String getTitle() {
	    return title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  public String getContent() {
	    return content;
	  }

	  public void setContent(String content) {
	    this.content = content;
	  }

	  public String getWriter() {
	    return writer;
	  }

	  public void setWriter(String writer) {
	    this.writer = writer;
	  }

	  public Date getWriteDate() {
	    return WriteDate;
	  }

	  public void getWriteDate(Date WriteDate) {
	    this.WriteDate = WriteDate;
	  }
	}