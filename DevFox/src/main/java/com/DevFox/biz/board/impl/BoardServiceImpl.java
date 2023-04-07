package com.DevFox.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DevFox.biz.board.BoardService;
import com.DevFox.biz.board.BoardVO;
import com.DevFox.biz.common.Log4jAdvice;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
private Log4jAdvice log;
@Autowired
private BoardDAO boardDAO;
public BoardServiceImpl() {
    log = new Log4jAdvice();
}

@Override
public void insertBoard(BoardVO vo) {
    boardDAO.insertBoard(vo);
    log.printLogging();
}

@Override
public void increaseCount(int seq) {
    boardDAO.increaseCount(seq);
    log.printLogging();
}

@Override
public void updateLike(BoardVO vo) {
    boardDAO.updateLike(vo);
    log.printLogging();
}

@Override
public void updateBad(BoardVO vo) {
    boardDAO.updateBad(vo);
    log.printLogging();
}

@Override
public void updateBoard(BoardVO vo) {
    boardDAO.updateBoard(vo);
    log.printLogging();
}

@Override
public void deleteBoard(BoardVO vo) {
    boardDAO.deleteBoard(vo);
    log.printLogging();
}

@Override
public BoardVO getBoard(BoardVO vo) {
    log.printLogging();
    return boardDAO.getBoard(vo);
}

@Override
public List<BoardVO> getBoardList(BoardVO vo) {
    log.printLogging();
    return boardDAO.getBoardList(vo);
}
}