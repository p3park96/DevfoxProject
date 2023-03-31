package com.globalin.biz.user.impl;

import java.sql.*;

import org.springframework.stereotype.Repository;

import com.globalin.biz.common.JDBCUtil;
import com.globalin.biz.user.UserVO;

@Repository("userDAO")
public class UserDAO {

	// JDBC 관련 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// SQL 명령어
	private final String USER_GET="select * from users where id=? and password=?";
	
	// CRUD 기능의 메소드 구현
	public UserVO getUser(UserVO vo){
		
		UserVO user = null;
		try {
			System.out.println("----> JDBC로 getUser() 기능 처리....");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		
		return user;
	}
}
