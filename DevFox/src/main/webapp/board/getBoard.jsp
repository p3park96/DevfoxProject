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
<h1>글 상세보기</h1>
<h3>
<c:if test="${empty userName}">
    <a href="login.do"><spring:message code="message.user.login.title" /></a>
</c:if>
<c:if test="${not empty userName}">
    <a href="logout.do"><spring:message code="message.user.logout.title" /></a>
</c:if>
</h3>
<hr>
<form action="updateBoard.do" method="post">
    <input type="hidden" name="seq" value="${board.seq }">
<table border="1" cellpadding="0" cellspacing="0">
<tr>
    <td bgcolor="orange">제목</td>
    <td align="left">
        <input type="text" name="title" value="${board.title }" readonly>
    </td>
</tr>
    

<tr>
    <td bgcolor="orange" width="70">작성자</td>
    <td align="left">&nbsp;${board.writer}</td>
</tr>
    
   <tr>
    <td bgcolor="orange">내용</td>
    <td align="left">
        <textarea name="content" cols="40" rows="10" readonly>${board.content }</textarea>
    </td>
</tr>
    
    <tr>
        <td bgcolor="orange">등록일</td>
        <td align="left">&nbsp;${board.regDate }
        </td>
    </tr>
    
    <tr>
        <td bgcolor="orange" width="70">조회수</td>
        <td align="left">&nbsp;${board.cnt }
        </td>
    </tr>
    
    <c:if test="${board.writer eq sessionScope.userName}">
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="글수정">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <a href="deleteBoard.do?seq=${board.seq }">글 삭제</a>
            </td>
        </tr>
    </c:if>
</table>
</form>
<hr>
<a href="writeBoard.do">글 등록</a>&nbsp;&nbsp;&nbsp;
<a href="getBoardList.do">글 목록</a>
<!-- 댓글 목록 -->
<h2>댓글 목록</h2>
<c:forEach items="${commentList}" var="comment">
    <div>
        <p><strong>${comment.writer}</strong></p>
        <p>${comment.content}</p>
        <p>${comment.regDate}</p>
    </div>
</c:forEach>
<!-- 댓글 입력 폼 -->
<h2>댓글 입력</h2>
<form action="writeComment.do" method="post">
    <input type="hidden" name="boardSeq" value="${board.seq}">
    <p>
        작성자 : <input type="text" name="writer">
    </p>
    <p>
        내용 : 
        <textarea name="content" cols="40" rows="5"></textarea>
    </p>
    <p>
        <input type="submit" value="댓글 등록">
    </p>
</form>
</div>
</body>
</html>