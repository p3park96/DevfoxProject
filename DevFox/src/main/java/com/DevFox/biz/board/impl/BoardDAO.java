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

@Repository
public class BoardDAO {
	
 @Autowired
  private DataSource dataSource;


  private final String INSERT_BOARD_SQL = "INSERT INTO BOARD (BOARD_ID, TITLE, CONTENT, WRITER, WRITE_DATE) VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, ?)";
  private final String SELECT_BOARD_BY_ID_SQL = "SELECT * FROM BOARD WHERE BOARD_ID = ?";
  private final String SELECT_ALL_BOARDS_SQL = "SELECT * FROM BOARD";
  private final String UPDATE_BOARD_SQL = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BOARD_ID = ?";
  private final String DELETE_BOARD_SQL = "DELETE FROM BOARD WHERE BOARD_ID = ?";

  public void createBoard(BoardVO board) throws SQLException {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(INSERT_BOARD_SQL)) {
      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setString(3, board.getWriter());
      stmt.setDate(4, new java.sql.Date(board.getWriteDate().getTime()));
      stmt.executeUpdate();
    }
  }

  public BoardVO getBoardById(int boardId) throws SQLException {
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

  public List<BoardVO> getAllBoards() throws SQLException {
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