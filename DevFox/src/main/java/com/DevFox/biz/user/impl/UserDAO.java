package com.DevFox.biz.user.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DevFox.biz.user.UserVO;

@Repository
public class UserDAO {
  private final String INSERT_USER_SQL = "INSERT INTO USERS (USER_ID, USERNAME, PASSWORD, EMAIL, REG_DATE) VALUES (USER_SEQ.NEXTVAL, ?, ?, ?, ?)";
  private final String SELECT_USER_BY_ID_SQL = "SELECT * FROM USERS WHERE USER_ID = ?";
  private final String SELECT_ALL_USERS_SQL = "SELECT * FROM USERS";
  private final String UPDATE_USER_SQL = "UPDATE USERS SET USERNAME = ?, PASSWORD = ?, EMAIL = ? WHERE USER_ID = ?";
  private final String DELETE_USER_SQL = "DELETE FROM USERS WHERE USER_ID = ?";
  @Autowired
  private DataSource dataSource;

  public void createUser(UserVO user) throws SQLException {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(INSERT_USER_SQL)) {
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getEmail());
      stmt.setDate(4, new java.sql.Date(user.getRegDate().getTime()));
      stmt.executeUpdate();
    }
  }

  public UserVO getUserById(int userId) throws SQLException {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_ID_SQL)) {
      stmt.setInt(1, userId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new UserVO(rs.getInt("USER_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("EMAIL"), rs.getDate("REG_DATE"));
      }
    }
    return null;
  }

  public List<UserVO> getAllUsers() throws SQLException {
    List<UserVO> userList = new ArrayList<>();
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_USERS_SQL)) {
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        userList.add(new UserVO(rs.getInt("USER_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("EMAIL"), rs.getDate("REG_DATE")));
      }
    }
    return userList;
  }

  public void updateUser(UserVO user) throws SQLException {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_USER_SQL)) {
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getEmail());
      stmt.setInt(4, user.getUserId());
      stmt.executeUpdate();
    }
  }

  public void deleteUser(int userId) throws SQLException {
    try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DELETE_USER_SQL)) {
      stmt.setInt(1, userId);
      stmt.executeUpdate();
    }
  }
}
