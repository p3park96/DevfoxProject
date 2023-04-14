package com.DevFox.biz.exam;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.DevFox.biz.board.BoardVO;

public interface ExamService {

	void insertExam(ExamVO vo);

	void updateExam(ExamVO vo);

	void deleteExam(ExamVO vo);

	ExamVO getExam(ExamVO vo);

	List<ExamVO> getExamList(boolean type);

	void updateCnt(ExamVO vo);
	
	void updateFav(ExamVO vo);

	ExamVO getExam(int i);
	


}
