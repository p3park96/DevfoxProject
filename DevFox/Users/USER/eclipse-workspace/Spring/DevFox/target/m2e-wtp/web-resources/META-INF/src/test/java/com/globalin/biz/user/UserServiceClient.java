package com.globalin.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {

	public static void main(String[] args) {
		
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		UserService userService = (UserService)container.getBean("userService");
		
		
		// 로그인 기능구현
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test123");
		
		UserVO user = userService.getUser(vo);
		
		if(user!=null) {
			System.out.println(user.getName()+"님 무쟈게 환영하는 바입니다.");
		}else {
			System.out.println("로그인 실패 !!! ..");
		}
		
		container.close();

	}

}
