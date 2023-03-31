package com.globalin.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.globalin.biz.board.BoardService;
import com.globalin.biz.board.BoardVO;
import com.globalin.biz.board.impl.BoardDAO;

//반환형 타입은 String, ModelAndView 둘 중 어느쪽을 사용해도 같으니 개발자 마음대로 활용

@Controller
@SessionAttributes("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 글 등록
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IOException {
		System.out.println("글 등록 처리..");
		// 파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile(); 
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("C:/aa/"+fileName));
		}
		
		
		// DB 연동 처리
		boardService.insertBoard(vo);
		
		return "getBoardList.do";
	}
	/*
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println("글 등록 처리..");
		boardDAO.insertBoard(vo);
		
		return "getBoardList.do";
	}
	*/
	
	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		
		System.out.println("글 수정 처리..");
		/*
		System.out.println("번호 : " + vo.getSeq());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성자 : " + vo.getWriter());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getRegDate());
		System.out.println("조회수 : " + vo.getCnt());
		*/
		boardService.updateBoard(vo);
		
		return "getBoardList.do";
	}
	/*
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo, BoardDAO boardDAO) {
		
		System.out.println("글 수정 처리..");
		
		System.out.println("번호 : " + vo.getSeq());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성자 : " + vo.getWriter());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("등록일 : " + vo.getRegDate());
		System.out.println("조회수 : " + vo.getCnt());
		boardDAO.updateBoard(vo);
		
		return "getBoardList.do";
	}
	*/
	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		
		System.out.println("글 삭제 처리..");
		boardService.deleteBoard(vo);
		
		return "getBoardList.do";
	}
	/*
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		
		System.out.println("글 삭제 처리..");
		boardDAO.deleteBoard(vo);
		
		return "getBoardList.do";
	}
	*/
	
	// 글 상세조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		
		System.out.println("글 상세 조회 처리..");
		
		// Model 정보 저장
		model.addAttribute("board", boardService.getBoard(vo));
		
		return "getBoard.jsp"; // View 이름을 리턴함
	}
	/*
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		
		System.out.println("글 상세 조회 처리..");
		
		// Model 정보 저장
		model.addAttribute("board", boardDAO.getBoard(vo));
		
		return "getBoard.jsp"; // View 이름을 리턴함
	}
	*/
	/*
	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {
		
		System.out.println("글 상세 조회 처리..");
		mav.addObject("board", boardDAO.getBoard(vo));
		mav.setViewName("getBoard.jsp");
		
		return mav;
	}
	*/
	
	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}
	
	
	/*
	// 글 목록조회
	@RequestMapping("/getBoardList.do")
	public String getBoardList(
			@RequestParam(value="searchCondition", defaultValue="TITLE", required=false) String condition, 
			@RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword
			, BoardDAO boardDAO, Model model) {
		
		System.out.println("글 목록 검색 처리..");
		
		
		System.out.println("검색 조건 : " + condition);
		System.out.println("검색 단어 : " + keyword);
		model.addAttribute("boardList", boardDAO.getBoardList());
		
		return "getBoardList.jsp";
	}
	*/
	/*
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {
		
		System.out.println("글 목록 검색 처리..");
		mav.addObject("boardList", boardDAO.getBoardList());
		mav.setViewName("getBoardList.jsp");
		
		return mav;
	}
	*/
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, BoardDAO boardDAO, Model model) {
		
		System.out.println("글 목록 검색 처리..");
		
		// Null Check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		// model
		model.addAttribute("boardList", boardService.getBoardList());
		
		return "getBoardList.jsp";
	}
	
}
