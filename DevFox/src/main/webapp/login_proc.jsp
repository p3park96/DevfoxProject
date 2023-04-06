<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.DevFox.biz.user.impl.UserServiceImpl" %>
<%@ page import="com.DevFox.biz.user.impl.UserDAO" %>
<%@ page import="com.DevFox.biz.user.UserVO" %>

<%
	// 1단계 - 사용자가 입력한 정보를 추출
	UserDAO userDAO = new UserDAO();
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	System.out.println(id);
	System.out.println(password);
	// 2단계 - DB연동 처리
	
	UserVO vo = new UserVO();
	System.out.println("vo생성 성공");
	vo.setId(id);
	vo.setPassword(password);
    System.out.println(vo.toString());
	UserVO user = userDAO.getUser(vo);
	System.out.println(user.getId());
	System.out.println(user.getNo());
	
	// 3단계 - 화면 네비게이션
	if(user!=null){
		response.sendRedirect("getBoardList.jsp");
	}else{
		response.sendRedirect("login.jsp");
	}
	
%>