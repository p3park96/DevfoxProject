package com.DevFox.biz.comment.impl;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.DevFox.biz.comment.CommentVO;
import com.DevFox.biz.common.JDBCUtil;

// DAO(Data Access Object)
@Repository("commentDAO")
public class CommentDAO {
	
	// JDBC 관련 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// SQL 명령어들
	private final String COMMENT_INSERT="insert into comments(commentNo, writer,content,password,boardNo) values((select nvl(max(commentNo),0)+1 from comments),?, ?, ?, ?,?)";
	private final String COMMENT_UPDATE="update comments set content=? where commentNo=?";
	private final String COMMENT_DELETE="delete from comments where commentNo=?";
	private final String COMMENT_GET="select * from comments where commentNo=?";
	private final String COMMENT_LIST="select * from comments order by commentNo desc";
	private final String COMMENT_LIKE_UPDATE = "update comments set likes = likes+1 where commentNo=?";
	private final String COMMENT_BAD_UPDATE = "update comments set bad = bad+1 where commentNo=?";
	
	// CRUD 메소드 구현
	// 글 등록
	
	public void insertComment(CommentVO vo) {
		
		System.out.println("====> JDBC로 insertComment() 기능 처리.");
		
		try {
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(COMMENT_INSERT);
			pstmt.setInt(1, vo.getCommentNo());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getPassword());
			pstmt.setInt(5, vo.getBoardNo());
			rs = pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
	}
	

	// 좋아요 증가
	
	public void updateLike(CommentVO vo) {
	    try {
	        conn = JDBCUtil.getConnection();
	        pstmt = conn.prepareStatement(COMMENT_LIKE_UPDATE);
	        pstmt.setInt(1, vo.getLike() + 1);
	        pstmt.setInt(2, vo.getCommentNo());
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(pstmt, conn);
	    }
	}

	// 싫어요 증가
	
	public void updateBad(CommentVO vo) {
	    try {
	        conn = JDBCUtil.getConnection();
	        pstmt = conn.prepareStatement(COMMENT_BAD_UPDATE);
	        pstmt.setInt(1, vo.getBad() + 1);
	        pstmt.setInt(2, vo.getCommentNo());
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(pstmt, conn);
	    }
	}
	// 글 수정
	
	public void updateComment(CommentVO vo) {
		
		System.out.println("====> JDBC로 updateComment() 기능 처리.");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(COMMENT_UPDATE);
			pstmt.setString(1, vo.getContent());
			pstmt.setInt(2, vo.getCommentNo());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		
	}
	
	
	// 글 삭제
	
	public void deleteComment(CommentVO vo) {
		
		System.out.println("====> JDBC로 deleteComment() 기능 처리.");
		
		try {
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(COMMENT_DELETE);
			pstmt.setInt(1, vo.getCommentNo());
			rs = pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		
	}
	
	
	// 글 상세조회
	
	public CommentVO getComment(CommentVO vo) {
		
		System.out.println("====> JDBC로 getComment() 기능 처리.");
		CommentVO comment = null;
		
		try {
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(COMMENT_GET);
			pstmt.setInt(1, vo.getCommentNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				comment = new CommentVO();
				comment.setCommentNo(rs.getInt("commentNo"));
				comment.setWriter(rs.getString("writer"));
				comment.setContent(rs.getString("content"));
				comment.setRegDate(rs.getDate("regDate"));
				comment.setBoardNo(rs.getInt("boardNo"));
				comment.setLike(rs.getInt("like"));
				comment.setBad(rs.getInt("Bad"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		return comment;
	}
	
	// 글 목록조회
	
	public List<CommentVO> getCommentList(CommentVO vo) {
		
		System.out.println("====> JDBC로 getCommentList() 기능 처리.");	
		List<CommentVO> commentList = new ArrayList<CommentVO>();
		CommentVO comment = null;
		try {
			
			conn = JDBCUtil.getConnection();
			
			pstmt = conn.prepareStatement(COMMENT_LIST);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				comment = new CommentVO();
				comment.setCommentNo(rs.getInt("commentNo"));
				comment.setWriter(rs.getString("writer"));
				comment.setContent(rs.getString("content"));
				comment.setRegDate(rs.getDate("regDate"));
				comment.setBoardNo(rs.getInt("boardNo"));
				comment.setLike(rs.getInt("like"));
				comment.setBad(rs.getInt("Bad"));
				commentList.add(comment);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return commentList;
	}
}
