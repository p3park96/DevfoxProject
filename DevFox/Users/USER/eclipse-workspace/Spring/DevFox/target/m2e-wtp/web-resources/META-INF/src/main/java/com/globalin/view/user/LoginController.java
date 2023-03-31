package com.globalin.view.user;


import java.lang.annotation.Annotation;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.globalin.biz.user.UserVO;
import com.globalin.biz.user.impl.UserDAO;

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
		
		/*
		// 1단계 - 사용자가 입력한 정보를 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2단계 - DB연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);

		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);

		// 3단계 - 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		*/
		UserVO user = userDAO.getUser(vo);
		
		if (userDAO.getUser(user) != null) {
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		//	mav.setViewName("redirect:getBoardList.do");
		} else {
			return "login.jsp";
		//	mav.setViewName("redirect:login.jsp");
		}
		
		// return mav;

	}

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return null;
	}

}
