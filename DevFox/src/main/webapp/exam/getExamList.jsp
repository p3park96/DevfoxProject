<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Exam List</title>
</head>

<body>
 
<a href="index.jsp">Go to index page</a>

<a href="getExamList.do?wrong=false">전체 노트</a>
<a href="getExamList.do?wrong=true">오답 노트</a>


</form>
	<h2>Exam List</h2>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Question</th>
				<th>Favorite</th>
				<th>Count</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="page" value="1" />
			<c:if test="${not empty param.page}">
				<c:set var="page" value="${param.page}" />
			</c:if>
			<c:set var="start" value="${(page-1)*20}" />
			<c:choose>
				<c:when test="${examList.size() - start < 20}">
					<c:set var="end" value="${examList.size()}" />
				</c:when>
				<c:otherwise>
					<c:set var="end" value="${start + 20}" />
				</c:otherwise>
			</c:choose>
			<c:set var="pageList" value="${examList.subList(start, end)}" />
			<c:forEach items="${pageList}" var="exam">
				<tr>
					<td>${exam.id}</td>
					<td><a href="getExam.do?id=${exam.id }">${exam.question}</a></td>
					<td>
						<c:choose>
							<c:when test="${exam.favorite == 1}">YES</c:when>
							<c:when test="${exam.favorite == 0}">NO</c:when>
						</c:choose>
					</td>
					<td>${exam.cnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${examList.size() > 20}">
	<p>
		<c:url value="getExamList.do" var="url" />
		<c:if test="${page > 1}">
			<a href="${url}?page=${page-1}">이전 페이지</a>
		</c:if>
		
	
		
		<c:forEach begin="1" end="${(examList.size()-1)/20+1}" var="i">
			<c:url value="getExamList.do" var="url">
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
		
		<c:url value="getExamList.do" var="url" />
		<c:if test="${page < (examList.size()-1)/20}">
			<a href="${url}?page=${page+1}">다음 페이지</a>
		</c:if>
	</p>
</c:if>

</body>
</html>