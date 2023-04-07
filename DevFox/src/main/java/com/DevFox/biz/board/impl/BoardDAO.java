package com.DevFox.biz.board.impl;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.DevFox.biz.board.BoardVO;
import com.DevFox.biz.common.JDBCUtil;

// DAO(Data Access Object)
@Repository("boardDAO")
public class BoardDAO {
	
	// JDBC 관련 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// SQL 명령어들
	private final String BOARD_INSERT="insert into board(seq, title, writer, content,type) values((select nvl(max(seq),0)+1 from board), ?, ?, ?,?)";
	private final String BOARD_UPDATE="update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE="delete from board where seq=?";
	private final String BOARD_GET="select * from board where seq=?";
	private final String BOARD_LIST="select * from board order by seq desc";
	private final String BOARD_LIST_T="select * from board where title like '%'||?||'%' order by seq desc"; 
	private final String BOARD_LIST_C="select * from board where content like '%'||?||'%' order by seq desc"; 
	private final String BOARD_LIKE_UPDATE = "update board set like = like+1 where seq=?";
	private final String BOARD_BAD_UPDATE = "update board set bad = bad+1 where seq=?";
	private final String BOARD_CNT_UPDATE = "update board set cnt = cnt+1 where seq=?";
	
	// CRUD 메소드 구현
	// 글 등록
	
	public void insertBoard(BoardVO vo) {
		
		System.out.println("====> JDBC로 insertBoard() 기능 처리.");
		
		try {
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getPassword());
			pstmt.setInt(5, vo.getType());
			rs = pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
	}
	
	
	public void increaseCount(int seq) {
	    try {
	        conn = JDBCUtil.getConnection();
	        pstmt = conn.prepareStatement(BOARD_CNT_UPDATE);
	        pstmt.setInt(1, seq);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(pstmt, conn);
	    }
	}

	// 좋아요 증가
	
	public void updateLike(BoardVO vo) {
	    try {
	        conn = JDBCUtil.getConnection();
	        pstmt = conn.prepareStatement(BOARD_LIKE_UPDATE);
	        pstmt.setInt(1, vo.getLike() + 1);
	        pstmt.setInt(2, vo.getSeq());
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(pstmt, conn);
	    }
	}

	// 싫어요 증가
	
	public void updateBad(BoardVO vo) {
	    try {
	        conn = JDBCUtil.getConnection();
	        pstmt = conn.prepareStatement(BOARD_BAD_UPDATE);
	        pstmt.setInt(1, vo.getBad() + 1);
	        pstmt.setInt(2, vo.getSeq());
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(pstmt, conn);
	    }
	}
	// 글 수정
	
	public void updateBoard(BoardVO vo) {
		
		System.out.println("====> JDBC로 updateBoard() 기능 처리.");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getSeq());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		
	}
	
	
	// 글 삭제
	
	public void deleteBoard(BoardVO vo) {
		
		System.out.println("====> JDBC로 deleteBoard() 기능 처리.");
		
		try {
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, vo.getSeq());
			rs = pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		
	}
	
	
	// 글 상세조회
	
	public BoardVO getBoard(BoardVO vo) {
		
		System.out.println("====> JDBC로 getBoard() 기능 처리.");
		BoardVO board = null;
		
		try {
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, vo.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));
				board.setLike(rs.getInt("like"));
				board.setBad(rs.getInt("Bad"));
				board.setType(rs.getInt("type"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		return board;
	}
	
	// 글 목록조회
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		System.out.println("====> JDBC로 getBoardList() 기능 처리.");	
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		BoardVO board = null;
		try {
			
			conn = JDBCUtil.getConnection();
			if(vo.getSearchCondition().equals("TITLE")) {
				pstmt = conn.prepareStatement(BOARD_LIST_T);
			}else if(vo.getSearchCondition().equals("CONTENT")) {
				pstmt = conn.prepareStatement(BOARD_LIST_C);
			}
			pstmt.setString(1, vo.getSearchKeyword());
			
			// pstmt = conn.prepareStatement(BOARD_LIST);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));
				board.setLike(rs.getInt("likes"));
				board.setBad(rs.getInt("Bads"));
				boardList.add(board);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return boardList;
	}
}
