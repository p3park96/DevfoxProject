package com.DevFox.biz.board.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DevFox.biz.board.BoardVO;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boardDAO")
public class BoardDAO {
	
 @Autowired
  private DataSource dataSource;


  private final String INSERT_BOARD_SQL = "INSERT INTO BOARD (TITLE, CONTENT, WRITER) VALUES (?, ?, ?)";
  private final String SELECT_BOARD_BY_ID_SQL = "SELECT * FROM BOARD WHERE BOARD_ID = ?";
  private final String SELECT_ALL_BOARDS_SQL = "SELECT * FROM BOARD";
  private final String UPDATE_BOARD_SQL = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BOARD_ID = ?";
  private final String DELETE_BOARD_SQL = "DELETE FROM BOARD WHERE BOARD_ID = ?";

  public int insertBoard(BoardVO board) throws SQLException {
	  int newId = -1;
	  try (Connection conn = dataSource.getConnection();
	      PreparedStatement stmt = conn.prepareStatement(INSERT_BOARD_SQL, new String[]{"BOARD_ID"})) {
	    stmt.setString(1, board.getTitle());
	    stmt.setString(2, board.getContent());
	    stmt.setString(3, board.getWriter());
	    int affectedRows = stmt.executeUpdate();
	    if (affectedRows == 0) {
	      throw new SQLException("Creating board failed, no rows affected.");
	    }
	    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	      if (generatedKeys.next()) {
	        newId = generatedKeys.getInt(1);
	      } else {
	        throw new SQLException("Creating board failed, no ID obtained.");
	      }
	    }
	  }
	  return newId;
	}

  public BoardVO getBoard(int boardId) throws SQLException {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(SELECT_BOARD_BY_ID_SQL)) {
      stmt.setInt(1, boardId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new BoardVO(rs.getInt("BOARD_ID"), rs.getString("TITLE"), rs.getString("CONTENT"), rs.getString("WRITER"), rs.getDate("WRITE_DATE"));
      }
    }
    return null;
  }

  public List<BoardVO> getBoardList() throws SQLException {
    List<BoardVO> boardList = new ArrayList<>();
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_BOARDS_SQL)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        boardList.add(new BoardVO(rs.getInt("BOARD_ID"), rs.getString("TITLE"), rs.getString("CONTENT"), rs.getString("WRITER"), rs.getDate("WRITE_DATE")));
      }
    }
    return boardList;
  }

  public void updateBoard(BoardVO board) throws SQLException {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_BOARD_SQL)) {
      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getBoardId());
      stmt.executeUpdate();
    }
  }

  public void deleteBoard(int boardId) throws SQLException {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DELETE_BOARD_SQL)) {
      stmt.setInt(1, boardId);
      stmt.executeUpdate();
    }
  }
}