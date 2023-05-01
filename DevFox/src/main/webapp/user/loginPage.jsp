	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title><spring:message code="message.user.login.title" /></title>
	</head>
	<body>
	<center></center>
	<div align="center">
	<h1><spring:message code="message.user.login.title" /></h1>
	<a href="login?lang=en">
		<spring:message code="message.user.login.language.en" />
	</a>&nbsp;&nbsp;
	<a href="login?lang=ko">
		<spring:message code="message.user.login.language.ko" />
	</a>&nbsp;&nbsp;
	<hr>
		<form class="px-4 py-3" action="/login" method="post">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange"><spring:message code="message.user.login.id" /></td>
				<td><input type="text" class="form-control" name="loginid" placeholder="example""></td>
			</tr>
			
			<tr>
				<td bgcolor="orange"><spring:message code="message.user.login.password" /></td>
				<td><input type="password" class="form-control" name="loginPwd"></td>
			</tr>
			  <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" class="btn btn-primary" value="<spring:message code="message.user.login.loginBtn" />">
				</td>
			</tr>
		</table>
	</form>
	<hr>
	<a href="${pageContext.request.contextPath}/getBoardList.do">글 목록 보기</a>&nbsp;&nbsp;<a href="signup.do">회원가입</a>
	</div>
	
	<c:if test="${not empty errorMsg}">
  <p style="color:red;">${errorMsg}</p>
</c:if>
	</body>
	</html>
	