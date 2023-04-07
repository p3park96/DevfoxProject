
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<h1>회원 가입</h1>
<form action="insertUser.do" method="post">
	<label for="id">아이디:</label>
	<input type="text" name="id" required><br>
	<label for="password">비밀번호:</label>
	<input type="password" name="password" required><br>
	<label for="name">이름:</label>
	<input type="text" name="name" required><br>
	<input type="submit" value="가입">
</form>
</body>
</html>