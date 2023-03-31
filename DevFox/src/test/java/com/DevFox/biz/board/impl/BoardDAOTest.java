package com.DevFox.biz.board.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DevFox.biz.board.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class BoardDAOTest {

	 @Autowired
	    private BoardDAO boardDAO;
	 
	 

	    @Before
	    public void setUp() {
	    }
    @Test
    public void testInsertBoard() {
        // given
        BoardVO vo = new BoardVO();
        vo.setTitle("테스트 제목");
        vo.setWriter("테스트 작성자");
        vo.setContent("테스트 내용");
        vo.setSeq(1);
        // when
        boardDAO.insertBoard(vo);

        // then
        assertNotNull(vo.getSeq());
    }

    @Test
    public void testUpdateBoard() {
        // given
        BoardVO vo = new BoardVO();
        vo.setSeq(1);
        vo.setTitle("수정된 제목");
        vo.setContent("수정된 내용");

        // when
        boardDAO.updateBoard(vo);

        // then
        BoardVO updatedVO = boardDAO.getBoard(vo);
        assertEquals(vo.getTitle(), updatedVO.getTitle());
        assertEquals(vo.getContent(), updatedVO.getContent());

        // Rollback
        vo.setTitle("처음 제목");
        vo.setContent("처음 내용");
        boardDAO.updateBoard(vo);
    }

    @Test
    public void testDeleteBoard() {
        // given
        BoardVO vo = new BoardVO();
        vo.setSeq(1);

        // when
        boardDAO.deleteBoard(vo);

        // then
        BoardVO deletedVO = boardDAO.getBoard(vo);
        assertNull(deletedVO);
    }

    @Test
    public void testGetBoard() {
        // given
        BoardVO vo = new BoardVO();
        vo.setSeq(1);

        // when
        BoardVO selectedVO = boardDAO.getBoard(vo);

        // then
        assertNotNull(selectedVO);
        assertEquals(vo.getSeq(), selectedVO.getSeq());
        assertEquals(vo.getTitle(), selectedVO.getTitle());
        assertEquals(vo.getWriter(), selectedVO.getWriter());
        assertEquals(vo.getContent(), selectedVO.getContent());
    }

    @Test
    public void testGetBoardList() {
        // given
        BoardVO vo = new BoardVO();

        // when
        List<BoardVO> boardList = boardDAO.getBoardList(vo);

        // then
        assertNotNull(boardList);
        assertTrue(boardList.size() > 0);
    }

}