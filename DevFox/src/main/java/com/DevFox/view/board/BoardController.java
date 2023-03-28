package com.DevFox.view.board;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.DevFox.biz.board.BoardVO;
import com.DevFox.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {
  
  @Autowired
  private BoardDAO boardDAO;

  // 게시글 목록 페이지 이동
  @RequestMapping(value = "/board/list", method = RequestMethod.GET)
  public String getBoardList(Model model) throws SQLException {
    List<BoardVO> boardList = boardDAO.getBoardList();
    model.addAttribute("boardList", boardList);
    return "board_list"; // board_list.jsp로 이동
  }

  // 게시글 상세 페이지 이동
  @RequestMapping(value = "/board/detail", method = RequestMethod.GET)
  public String getBoard(int boardId, Model model) throws SQLException {
    BoardVO board = boardDAO.getBoard(boardId);
    model.addAttribute("board", board);
    return "board_detail"; // board_detail.jsp로 이동
  }

  // 게시글 작성 페이지 이동
  @RequestMapping(value = "/board/write", method = RequestMethod.GET)
  public String writeBoardForm() {
    return "board_write"; // board_write.jsp로 이동
  }

  // 게시글 작성 처리
  @RequestMapping(value = "/board/write", method = RequestMethod.POST)
  public String writeBoard(BoardVO board) throws SQLException {
    int newId = boardDAO.insertBoard(board);
    board.setBoardId(newId);
    return "redirect:/board/list"; // 게시글 목록 페이지로 이동
  }

  // 게시글 수정 페이지 이동
  @RequestMapping(value = "/board/modify", method = RequestMethod.GET)
  public String modifyBoardForm(int boardId, Model model) throws SQLException {
    BoardVO board = boardDAO.getBoard(boardId);
    model.addAttribute("board", board);
    return "board_modify"; // board_modify.jsp로 이동
  }

  // 게시글 수정 처리
  @RequestMapping(value = "/board/modify", method = RequestMethod.POST)
  public String modifyBoard(BoardVO board) throws SQLException {
    boardDAO.updateBoard(board);
    return "redirect:/board/detail?boardId=" + board.getBoardId(); // 게시글 상세 페이지로 이동
  }

  // 게시글 삭제 처리
  @RequestMapping(value = "/board/delete", method = RequestMethod.GET)
  public String deleteBoard(int boardId) throws SQLException {
    boardDAO.deleteBoard(boardId);
    return "redirect:/board/list"; // 게시글 목록 페이지로 이동
  }

}