<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<style>

.board-detail {
    background-color: #fff;
    border: 1px solid #ccc;
    padding: 20px;
    margin: 20px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.board-detail h2 {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 10px;
}

.board-detail .meta {
    font-size: 14px;
    color: #888;
    margin-bottom: 10px;
}

.board-detail .content {
    font-size: 16px;
    line-height: 1.5;
    margin-bottom: 20px;
}

.board-detail .buttons {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
}

.board-detail .buttons button {
    padding: 10px 20px;
    margin-right: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    cursor: pointer;
    transition: all 0.3s;
}

.board-detail .buttons button:hover {
    background-color: #0069d9;
}

.board-detail .buttons .btn-modify {
    padding: 10px 20px;
    background-color: #dc3545;
    color: #fff;
    border: none;
    cursor: pointer;
    transition: all 0.3s;
}

.board-detail .buttons .btn-modify:hover {
    background-color: #c82333;
}

.board-detail .buttons .btn-delete {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    cursor: pointer;
    transition: all 0.3s;
}

.board-detail .buttons .btn-delete:hover {
    background-color: #0069d9;
}
</style>
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

<div class="board-detail">
    <h2>${board.title}</h2>
    <p class="meta">작성자: ${board.writer} | 등록일: ${board.regDate} | 조회수: ${board.cnt}</p>
    <div class="content">${board.content}</div>
    
    <c:if test="${board.writer eq sessionScope.userName}">
        <div class="buttons">
            <a href="update.do?seq=${board.seq }" class="btn-modify">글 수정</a>

            <a href="deleteBoard.do?seq=${board.seq }" class="btn-delete">글 삭제</a>
        </div>
    </c:if>
</div>
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