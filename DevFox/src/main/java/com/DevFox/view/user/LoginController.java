package com.DevFox.view.user;


import java.lang.annotation.Annotation;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.DevFox.biz.user.UserVO;
import com.DevFox.biz.user.impl.UserDAO;


@Controller
public class LoginController{
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) { // get 방식
		System.out.println("로그인 화면으로 이동 .......");
		vo.setId("test");
		vo.setPassword("test123");
		
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session) { // post 방식

	    System.out.println("로그인 인증 처리 .......");

	    
	    
	    
	    if(vo.getId()==null||vo.getId().equals("")) {
	        throw new IllegalArgumentException("아이디는 반드시 입력하셔야 합니다...");
	    }
	    
	    
	    UserVO user = userDAO.getUser(vo);
	    
	    
	    
	    if (user != null) {
	        session.setAttribute("userName", user.getName());
	        return "getBoardList.do";
	    } else {
	        return "login.jsp";
	    }
	}

}

