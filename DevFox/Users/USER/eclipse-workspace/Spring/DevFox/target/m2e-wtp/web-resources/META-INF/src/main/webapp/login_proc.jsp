<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.globalin.biz.user.impl.UserDAO" %>
<%@ page import="com.globalin.biz.user.UserVO" %>

<%
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
	if(user!=null){
		response.sendRedirect("getBoardList.jsp");
	}else{
		response.sendRedirect("login.jsp");
	}
	
%>