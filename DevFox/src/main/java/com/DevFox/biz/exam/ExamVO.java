package com.DevFox.biz.exam;

public class ExamVO {
    private int id;
    private String question;
    private String answer;
    private String hint;
    private int answerElementCount;
    private int cnt;
    private int favorite;
    
    
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getFavorite() {
		return favorite;
	}
	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public int getAnswerElementCount() {
		return answerElementCount;
	}
	public void setAnswerElementCount(int answerElementCount) {
		this.answerElementCount = answerElementCount;
	}
	@Override
	public String toString() {
		return "ExamVO [id=" + id + ", question=" + question + ", answer=" + answer + ", hint=" + hint
				+ ", answerElementCount=" + answerElementCount + ", cnt=" + cnt + ", favorite=" + favorite + "]";
	}
    

}