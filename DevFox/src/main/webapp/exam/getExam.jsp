<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Exam</title>
	<style>
  .hint-container {
    display: flex;
    align-items: center;
  }

  #hint,
  #answer{
    display: none;
  }
</style>
	
	<script>
	function showAnswer() {
		var answer = document.getElementById("answer");
		answer.style.display = "block";
		
		
		var answerbutton = document.getElementById("answerbutton");
		answerbutton.style.display = "none";
	}
	
	function reset() {
	
		var answer = document.getElementById("answer");
		answer.style.display = "none";
		var answerbutton = document.getElementById("answerbutton");
		answerbutton.style.display = "block";
		  for (var i = 1; i <= ${exam.answerElementCount}; i++) {
		        document.getElementById("inp" + i).value = "";
		    }
	}

	function handleKeyPress(event) {
		if (event.keyCode === 13) {
			showAnswer();
		}
	}
	

		function toggleHint() {
			var hint = document.getElementById("hint");
			var button = document.getElementById("hintButton");
			if (hint.style.display === "none") {
				hint.style.display = "block";
				button.innerHTML = "숨기기";
			} else {
				hint.style.display = "none";
				button.innerHTML = "힌트";
			}
		}
		
		  function nextQuestion(mo) {
			  var currentQuestion = window.location.href.match(/getExam.do\?id=(\d+)/)[1];
		        console.log(currentQuestion);
		        var nextQuestion = parseInt(currentQuestion) + 1;
		        console.log(nextQuestion);
		        window.location.href = "/getExam.do?id=" + nextQuestion;
		    }
		

		
		window.onload = function() {
			toggleHint();
			document.addEventListener("keydown", handleKeyPress);
		};
	</script>
</head>
<body>
	<a href="getExamList.do">문제 리스트</a> <a href="getExamList.do?wrong=true">오답 노트</a>
	<h2>문제 번호 ${exam.id}</h2>
	<p>문제 내용 : ${exam.question} (답변 ${exam.answerElementCount}개를 쓰시오)</p>
	<p>
			<div class="hint-container">
				<button id="hintButton" onclick="toggleHint()">힌트</button>
				<div id="hint">${exam.hint}</div>
			</div>
		</p>
	
   <c:forEach begin="1" end="${exam.answerElementCount}" var="i">
    <p>
        답안 입력칸 ${i} : <input type="text" id="inp${i}" ${i == 1 ? 'autofocus' : ''}>
    </p>
</c:forEach>

<hr />

<button id = "answerbutton" onclick="showAnswer()">답안 보기</button>

<div id="answer">
  ${exam.answer} <hr />
<form action="nextExam.do" method="post">
  <button id="button1" style="display:inline-block;" onclick="document.getElementById('hiddenInput').value = 0; this.form.submit();">몰?루?</button>

  <button id="button3" style="display:inline-block;" onclick="document.getElementById('hiddenInput').value = 1; this.form.submit();">아!루!</button>
  <input type="hidden" id="hiddenInput" name="favorite" value="0">
  <input type="hidden" name="id" value="${exam.id}">
</form>
  <button id="button2" style="display:inline-block;" onclick="reset()">까?비?</button>
</div>	
</body>
</html>