package com.DevFox.view.board;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import com.DevFox.biz.board.BoardService;
import com.DevFox.biz.board.BoardVO;
import com.DevFox.biz.board.impl.BoardServiceImpl;

@Controller
@SessionAttributes("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/writeBoard.do")
	public String writeBoard(BoardVO vo) throws IOException {
		System.out.println("글 작성 처리..");

		
		return "board/writeBoard.jsp";
	}
	
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IOException {
		System.out.println("글 등록 처리..");
		// DB 연동 처리
		boardService.insertBoard(vo);
		
		return "getBoardList.do";
	}

	@RequestMapping("/update.do")
	public String update(BoardVO vo, Model model) {
	    BoardVO board = boardService.getBoard(vo);
	    model.addAttribute("board", board);
	    return "board/updateBoard.jsp";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		
		System.out.println("글 수정 처리..");

		boardService.updateBoard(vo);
		
		return "getBoardList.do";
	}

	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		
		System.out.println("글 삭제 처리..");
		boardService.deleteBoard(vo);
		
		return "getBoardList.do";
	}

	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
	    BoardVO board = boardService.getBoard(vo);
	    model.addAttribute("board", board);
	    boardService.increaseCount(vo.getSeq()); // 글 조회 수 증가
	    return "board/getBoard.jsp";
	}

	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}
	
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, BoardServiceImpl boardDAO, Model model) {
		
		System.out.println("글 목록 검색 처리..");
		
	 
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		model.addAttribute("boardList", boardService.getBoardList(vo));
		
		return "board/getBoardList.jsp";
	}
	
}