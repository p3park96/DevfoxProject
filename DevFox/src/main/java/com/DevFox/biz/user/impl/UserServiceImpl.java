package com.DevFox.biz.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DevFox.biz.user.UserService;
import com.DevFox.biz.user.UserVO;
import com.DevFox.biz.common.Log4jAdvice;


@Service("usersService")
public class UserServiceImpl implements UserService {
	private Log4jAdvice log;
	@Autowired
private UserDAO userDAO;

	public UserServiceImpl() {

		log = new Log4jAdvice();
		
	}


	@Override
	public void insertUser(UserVO vo) {

		userDAO.insertUser(vo); 

		log.printLogging();
	}

	@Override
	public void updateUser(UserVO vo) {

		userDAO.updateUser(vo);
		log.printLogging();
	}

	@Override
	public void deleteUser(UserVO vo) {

		userDAO.deleteUser(vo);
		log.printLogging();
	}

	@Override
	public UserVO getUser(UserVO vo) {
		log.printLogging();
		return userDAO.getUser(vo);
	}

	@Override
	public List<UserVO> getUserList(UserVO vo) {
		log.printLogging();
		return userDAO.getUserList(vo);
	}

}
