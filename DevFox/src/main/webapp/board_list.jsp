<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
</head>
<body>
    <h1>게시글 목록</h1>
    <hr>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        <c:forEach items="${boardList}" var="board">
            <tr>
                <td>${board.boardId}</td>
                <td><a href="/board/detail?boardId=${board.boardId}">${board.title}</a></td>
                <td>${board.writer}</td>
                <td>${board.regDate}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="/board/write">새 글 작성</a>
</body>
</html>