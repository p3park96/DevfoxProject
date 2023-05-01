package com.DevFox.view.Exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.DevFox.biz.exam.ExamService;
import com.DevFox.biz.exam.ExamVO;

@Controller
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	//1. 시험 문제 추가하기
	//2. 시험 문제 리스트 정렬하기
	//3. 클릭한 시험 문제 표시하기
	//4. 다음 시험 문제 표시하기
	//5. 이전 시험 문제 표시하기
	//6. 
	
	@RequestMapping("/getExam.do")
	public String getExam(ExamVO vo, Model model) {
	    ExamVO exam = examService.getExam(vo);
	    exam.setCnt(exam.getCnt() + 1);
	    examService.updateCnt(exam);
	    
	    // examList 모델 사용
	    model.addAttribute("exam", exam);
	    return "exam/getExam.jsp";
	}
	
	@RequestMapping(value = "/nextExam.do", method = RequestMethod.POST)
	public String nextExam(@RequestParam("favorite") int fav,
	                       @RequestParam("id") int id,
	                       Model model,
	                       @ModelAttribute("examList") List<ExamVO> examList) {

	    // VO 설정
	    ExamVO exam = new ExamVO();
	    exam.setId(id);
	    exam.setFavorite(fav);

	    // updateFav 서비스 실행
	    examService.updateFav(exam);

	    // 다음 문제 조회
	    ExamVO newexam = null;
	    for (int i = 0; i < examList.size() - 1; i++) {
	        if (examList.get(i).getId() == id) {
	            newexam = examList.get(i+1);
	            break;
	        }
	    }

	    if (newexam == null) {
	        // 마지막 문제인 경우, 결과 페이지 출력
	        return "resultExam";
	    } else {
	        newexam.setCnt(newexam.getCnt() + 1);
	        examService.updateCnt(newexam);
	        model.addAttribute("exam", newexam);
	        return "exam/getExam.jsp";
	    }
	}

	
    @RequestMapping("/getExamList.do")
    public String getExamList(@RequestParam(value = "wrong", required = false, defaultValue = "false") boolean wrong, ExamVO vo, Model model) {
        System.out.println("시험 목록 검색 처리..");
            model.addAttribute("examList", examService.getExamList(wrong));
        return "exam/getExamList.jsp";
    }
    
	@ModelAttribute("exam")
	public ExamVO exam() {
		return new ExamVO();
	}
	
	@ModelAttribute("examList")
	public List<ExamVO> examList(@RequestParam(value = "wrong", defaultValue = "false") boolean wrong) {
	    return examService.getExamList(wrong);
	}

}