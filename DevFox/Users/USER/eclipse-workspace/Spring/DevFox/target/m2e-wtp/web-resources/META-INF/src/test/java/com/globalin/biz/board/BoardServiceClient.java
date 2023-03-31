package com.globalin.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.globalin.biz.board.impl.BoardDAO;
import com.globalin.biz.board.impl.BoardServiceImpl;

public class BoardServiceClient {

	public static void main(String[] args) {

		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		BoardService boardService = (BoardService) container.getBean("boardService");
		// BoardServiceImpl boardService = new BoardServiceImpl();
		// 글 등록
		/*
		BoardVO vo = new BoardVO();

		// vo.setSearchCondition("TITLE");
		// vo.setSearchKeyword("");
		// vo.setSeq(0); // 시퀀스는 0번 값을 가질 수 없음
		vo.setTitle("임시 제목");
		vo.setWriter("홍길동");
		vo.setContent("Spring Mybatis 내용..................");
		// vo.setContent("임시 내용들입니다. ......");
		boardService.insertBoard(vo);

		// 글 목록 검색
		List<BoardVO> boardList = boardService.getBoardList(vo);

		for (BoardVO board : boardList) {
			System.out.println("----> " + board.toString());
		}

		container.close();
		*/
		
		
		//BoardDAO boardDAO = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo.setTitle("임시 제목");
		vo.setWriter("홍길동");
		vo.setContent("Spring Mybatis 내용..................");
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		// vo.setContent("임시 내용들입니다. ......");
		boardService.insertBoard(vo);
		// 글 목록 검색
		List<BoardVO> boardList = boardService.getBoardList(vo);

		for (BoardVO board : boardList) {
			System.out.println("----> " + board.toString());
		}
		
		container.close();
	}

}
