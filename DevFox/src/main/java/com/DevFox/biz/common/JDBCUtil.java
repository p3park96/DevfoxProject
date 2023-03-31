package com.DevFox.biz.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JDBCUtil {

	  private DataSource dataSource;

	  public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	  }

	  public Connection getConnection() throws SQLException {
	   return dataSource.getConnection();
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