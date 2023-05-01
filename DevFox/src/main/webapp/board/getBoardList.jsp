<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="message.board.list.mainTitle" /></title>
</head>
<body>
<div align="center">
<h1><spring:message code="message.board.list.mainTitle" /></h1>
	<h3>
<c:if test="${empty userName}">
    <a href="login.do"><spring:message code="message.user.login.title" /></a>&nbsp;&nbsp;<a href="signup.do">회원가입</a>
</c:if>
<c:if test="${not empty userName}">
    ${userName }<spring:message code="message.board.list.welcomeMsg" />....<a href="logout.do"><spring:message code="message.user.logout.title" /></a>
    
</c:if>
</h3>

	
	
<c:set var="page" value="1" />


<c:if test="${not empty param.page}">
<c:set var="page" value="${param.page}" />
</c:if>

<c:set var="start" value="${(page-1)*10}" />
<c:choose>
<c:when test="${boardList.size() - start < 10}">
<c:set var="end" value="${boardList.size()}" />
</c:when>
<c:otherwise>
<c:set var="end" value="${start + 10}" />
</c:otherwise>
</c:choose>
<c:set var="pageList" value="${boardList.subList(start, end)}" />

	<table border="1" cellpadding="0" cellspacing="0" width="700">
		<tr>
			<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.seq" /></th>
			<th bgcolor="orange" width="200"><spring:message code="message.board.list.table.head.title" /></th>
			<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.writer" /></th>
			<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.regDate" /></th>
			<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.cnt" /></th>
		</tr>
	
<c:forEach items="${pageList}" var="board">
        <tr>
            <td>${board.seq }</td>
            <td align="left">
                <a href="getBoard.do?seq=${board.seq }">${board.title }</a>
            </td>
            <td>${board.writer }</td>
            <td>${board.regDate }</td>
            <td>${board.cnt }</td>
        </tr>
</c:forEach>
</table>

<c:if test="${boardList.size() > 10}">
<p>
<c:url value="getBoardList.do" var="url" />
<c:if test="${page > 1}">
<a href="${url}?page=${page-1}">이전 페이지</a>
</c:if>
<c:forEach begin="1" end="${(boardList.size()-1)/10+1}" var="i">
<c:url value="getBoardList.do" var="url">
<c:param name="page" value="${i}" />
</c:url>
<c:choose>
<c:when test="${page == i}">
[${i}]
</c:when>
<c:otherwise>
<a href="${url}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:url value="getBoardList.do" var="url" />
		<c:if test="${page < (boardList.size()-1)/10}">
			<a href="${url}?page=${page+1}">다음 페이지</a>
		</c:if>
	</p>
</c:if>


	<br>
	<a href="writeBoard.do"><spring:message code="message.board.list.link.insertBoard" /></a>
</div>
</body>
</html>