package com.DevFox.biz.user.impl;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DevFox.biz.user.UserVO;
import com.DevFox.biz.common.JDBCUtil;

// DAO(Data Access Object)
@Repository("userDAO")
public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

    @Autowired
    private DataSource dataSource;
	 
	// SQL 명령어들
	private final String user_INSERT="insert into users(no, id, name, password) values((select nvl(max(no),0)+1 from users), ?, ?, ?)";
	private final String user_UPDATE="update users set id=?, password=? where no=?";
	private final String user_DELETE="delete from users where no=?";
	private final String user_GET="SELECT * FROM users WHERE id=? AND password=?";
	private final String user_LIST="select * from users order by no desc";

	
	// CRUD 메소드 구현
	// 글 등록

	public void insertUser(UserVO vo) {
		
		System.out.println("====> JDBC로 insertuser() 기능 처리.");
		
		try {
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(user_INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassword());
			rs = pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
	}
	
	
	public void updateUser(UserVO vo) {
		
		System.out.println("====> JDBC로 updateuser() 기능 처리.");
		
		try {

			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(user_UPDATE);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setInt(3, vo.getNo());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		
	}
	
	
	public void deleteUser(UserVO vo) {
		
		System.out.println("====> JDBC로 deleteuser() 기능 처리.");
		
		try {
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(user_DELETE);
			pstmt.setInt(1, vo.getNo());
			rs = pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		
	}
	
	
	public UserVO getUser(UserVO vo) {
		
		System.out.println("====> JDBC로 getuser() 기능 처리.");
		UserVO user = new UserVO();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(user_GET);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();
		
			
			if(rs.next()) {
				user.setNo(rs.getInt("no"));
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRdate(rs.getDate("rdate"));
				user.setRole(rs.getInt("role"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		return user;
	}
	
	// 글 목록조회
	public List<UserVO> getUserList(UserVO vo) {
		
		System.out.println("====> JDBC로 getuserList() 기능 처리.");	
		List<UserVO> userList = new ArrayList<UserVO>();
		UserVO user = null;
		try {
			conn = dataSource.getConnection();
			System.out.println(dataSource.toString());
			
				pstmt = conn.prepareStatement(user_LIST);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user = new UserVO();
				user.setNo(rs.getInt("no"));
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRdate(rs.getDate("rdate"));
				user.setRole(rs.getInt("role"));
				userList.add(user);
				System.out.println("No: " + user.getNo() + "가 추가되었습니다.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
			
		return userList;
	}
}
