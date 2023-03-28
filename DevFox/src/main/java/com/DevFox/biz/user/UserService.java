package com.DevFox.biz.user;


import java.util.List;

public interface UserService {
  void createUser(UserVO user);
  UserVO getUserById(int userId);
  List<UserVO> getAllUsers();
  void updateUser(UserVO user);
  void deleteUser(int userId);
}