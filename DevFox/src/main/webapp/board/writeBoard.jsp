<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
</head>
<body>
<div align="center">
<h1>글 작성</h1>
<h3>
<c:if test="${empty userName}">
    <a href="login.do"><spring:message code="message.user.login.title" /></a>
</c:if>
<c:if test="${not empty userName}">
    <a href="logout.do"><spring:message code="message.user.logout.title" /></a>
</c:if>
</h3>
<hr>
<form action="insertBoard.do" method="post">
<table border="1" cellpadding="0" cellspacing="0">
    <tr>
        <td bgcolor="orange">제목</td>
        <td align="left">
            <input type="text" name="title">
        </td>
    </tr>
    
   <tr>
  <td bgcolor="orange">작성자</td>
  <%-- userName이 null이면 input 칸 숨기기 --%>
  <% if(session.getAttribute("userName") == null) { %>
    <td align="left"><input type="text" name="writer" size="10"></td>
  <% } else { %>
    <td align="left"><%= session.getAttribute("userName") %></td>
    <input type="hidden" name="writer" value="<%= session.getAttribute("userName") %>">
  <% } %>
</tr>
    
    <tr>
        <td bgcolor="orange">내용</td>
        <td align="left">
            <textarea name="content" cols="40" rows="10"></textarea>
        </td>
    </tr>
        <tr>
            <td colspan="2" align="center">
                	<input type="submit" value="글 등록">
            </td>
        </tr>
</table>
</form>
<hr>
<a href="getBoardList.do">글 목록</a>
<!-- 댓글 목록 -->

</form>
</div>
</body>
</html>