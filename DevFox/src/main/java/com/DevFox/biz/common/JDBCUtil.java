package com.DevFox.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##devfox", "1111");
	    } catch(ClassNotFoundException e) {
	        System.out.println("JDBC 드라이버를 찾을 수 없습니다.");
	        e.printStackTrace();
	    } catch(SQLException e) {
	        System.out.println("데이터베이스 연결에 실패하였습니다.");
	        e.printStackTrace();
	    }
		
		return null;
	}
	

  public static void close(PreparedStatement pstmt, Connection conn) {
    if (pstmt != null) {
      try {
        if (!pstmt.isClosed())
          pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        pstmt = null;
      }
    }

    if (conn != null) {
      try {
        if (!conn.isClosed())
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        conn = null;
      }
    }
  }

  public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
    if (rs != null) {
      try {
        if (!rs.isClosed())
          rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        rs = null;
      }
    }

    if (pstmt != null) {
      try {
        if (!pstmt.isClosed())
          pstmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        pstmt = null;
      }
    }

    if (conn != null) {
      try {
        if (!conn.isClosed())
          conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        conn = null;
      }
    }
  }
}