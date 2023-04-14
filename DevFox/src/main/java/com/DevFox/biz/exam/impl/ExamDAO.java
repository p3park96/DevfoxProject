package com.DevFox.biz.exam.impl;

import java.sql.*;
import java.util.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.DevFox.biz.common.JDBCUtil;
import com.DevFox.biz.exam.ExamVO;

@Repository("examDAO")
public class ExamDAO {
	
	private final String TEST_INSERT = "insert into TEST(ID,QUESTION,ANSWER,hint,ELEMENTCOUNT) values((select nvl(max(ID),0)+1 from TEST),?, ?,?,?)";
    private final String TEST_UPDATE = "update test set(QUESTION,ANSWER,hint,ELEMENTCOUNT,FAVORITE,CNT) values (?, ?,?,?,?,?) where ID=?";
    private final String TEST_UPDATE_CNT = "UPDATE test SET cnt=? WHERE id=?";
    private final String TEST_UPDATE_FAV = "UPDATE test SET FAVORITE=? WHERE id=?";
    private final String TEST_DELETE = "delete from test where ID=?";
    private final String TEST_GET = "select * from test where ID=?";
    private final String TEST_LIST = "select * from test order by ID asc";
    private final String TEST_WRONG = "select * from WRONG_NOTE order by ID asc";

    public void insertExam(ExamVO vo) {
        System.out.println("====> JDBC로 insertExam() 기능 처리.");
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_INSERT)) {
            pstmt.setString(1, vo.getQuestion());
            pstmt.setString(2, vo.getAnswer());
            pstmt.setString(3, vo.getHint());
            pstmt.setInt(4, vo.getAnswerElementCount());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateExam(ExamVO vo) {
        System.out.println("====> JDBC로 updateExam() 기능 처리.");
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_UPDATE)) {
            pstmt.setString(1, vo.getQuestion());
            pstmt.setString(2, vo.getAnswer());
            pstmt.setString(3, vo.getHint());
            pstmt.setInt(4, vo.getAnswerElementCount());
            pstmt.setInt(5, vo.getFavorite());
            pstmt.setInt(6, vo.getCnt());
            pstmt.setInt(7, vo.getId());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateCnt(ExamVO vo) {
        System.out.println("====> JDBC로 updateCNT() 기능 처리.");
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_UPDATE_CNT)) {
            pstmt.setInt(1, vo.getCnt());
            pstmt.setInt(2, vo.getId());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateFav(ExamVO vo) {
        System.out.println("====> JDBC로 updateFAV() 기능 처리.");
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_UPDATE_FAV)) {
            pstmt.setInt(1, vo.getFavorite());
            pstmt.setInt(2, vo.getId());
            pstmt.executeUpdate();
            System.out.println(vo.getFavorite());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteExam(ExamVO vo) {
        System.out.println("====> JDBC로 deleteExam() 기능 처리.");

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_DELETE)) {
            pstmt.setInt(1, vo.getId());
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ExamVO getExam(ExamVO vo) {
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_GET)) {
            pstmt.setInt(1, vo.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    vo = new ExamVO();
                    vo.setId(rs.getInt("ID"));
                    vo.setQuestion(rs.getString("QUESTION"));
                    vo.setAnswer(rs.getString("ANSWER"));
                    vo.setHint(rs.getString("HINT"));
                    vo.setAnswerElementCount(rs.getInt("ELEMENTCOUNT"));
                    vo.setFavorite(rs.getInt("FAVORITE"));
                    vo.setCnt(rs.getInt("CNT"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
    
    public ExamVO getExam(int i) {
    	ExamVO vo = new ExamVO();
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(TEST_GET)) {
            pstmt.setInt(1, i);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    
                    vo.setId(rs.getInt("ID"));
                    vo.setQuestion(rs.getString("QUESTION"));
                    vo.setAnswer(rs.getString("ANSWER"));
                    vo.setHint(rs.getString("HINT"));
                    vo.setAnswerElementCount(rs.getInt("ELEMENTCOUNT"));
                    vo.setFavorite(rs.getInt("FAVORITE"));
                    vo.setCnt(rs.getInt("CNT"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    public List<ExamVO> getExamList(boolean type) {
        List<ExamVO> list = new ArrayList<>();
        System.out.println(type);
        String sql;
        if (type == true) {
            sql =  TEST_WRONG;
            System.out.println("오답노트 출력");
        } else {
            sql = TEST_LIST;
            System.out.println("전체 출력");
        }
        
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ExamVO vo = new ExamVO();
                vo.setId(rs.getInt("ID"));
                vo.setQuestion(rs.getString("QUESTION"));
                vo.setAnswer(rs.getString("ANSWER"));
                vo.setHint(rs.getString("HINT"));
                vo.setAnswerElementCount(rs.getInt("ELEMENTCOUNT"));
                vo.setFavorite(rs.getInt("FAVORITE"));
                vo.setCnt(rs.getInt("CNT"));
                list.add(vo);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return list;
    }
    

	}