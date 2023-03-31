<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
</head>
<body>
<div align="center">
<h1>글 상세보기</h1>
<a href="logout.do">Log-out</a>
<hr>
<form action="updateBoard.do" method="post">
	<input type="hidden" name="seq" value="${board.seq }">
	
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="orange">제목</td>
			<td align="left">
				<input type="text" name="title" value="${board.title }">
			</td>
		</tr>
		
		<tr>
            <td bgcolor="orange" width="70">작성자</td>
            <td align="left">${board.writer}</td>
       </tr>
		
		<tr>
			<td bgcolor="orange">내용</td>
			<td align="left">
				<textarea name="content" cols="40" rows="10">${board.content }</textarea>
			</td>
		</tr>
		
		<tr>
			<td bgcolor="orange">등록일</td>
			<td align="left">
				<input type="text" name="regdate" value="${board.regDate }">
			</td>
		</tr>
		
		<tr>
			<td bgcolor="orange" width="70">조회수</td>
			<td align="left">
				<input type="text" name="cnt" value="${board.cnt }">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글수정">
			</td>
		</tr>
	</table>
</form>
<hr>
<a href="insertBoard.jsp">글 등록</a>&nbsp;&nbsp;&nbsp;
<a href="deleteBoard.do?seq=${board.seq }">글 삭제</a>&nbsp;&nbsp;&nbsp;
<a href="getBoardList.do">글 목록</a>
</div>
</body>
</html>