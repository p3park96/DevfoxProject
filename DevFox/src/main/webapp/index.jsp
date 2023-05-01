<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<body>
<div align="center">
<h1>게시판 프로그램</h1>
<hr>
<sec:authorize access="isAnonymous()">
    <h5><a href='<c:url value="/user/loginPage.do"/>'>LOGIN</a></h5>

</sec:authorize>
    <a href='<c:url value="/page"/>'>GUEST</a>
<a href='<c:url value="/user/page"/>'>USER</a>
<a href='<c:url value="/member/page"/>'>MEMBER</a>
<a href='<c:url value="/admin/page"/>'>ADMIN</a>

<br>
<br>
<br>
<a href="getBoardList.do">글 목록 바로가기</a>&nbsp;&nbsp;<a href="signup.do">회원가입</a>
<a href="getExamList.do">정보처리기사 실기 공부</a>


<hr>
</div>
</body>
</html>