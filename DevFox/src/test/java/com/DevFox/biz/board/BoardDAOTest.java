package com.DevFox.biz.board;



import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.DevFox.biz.board.impl.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BoardDAOTest {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private BoardDAO boardDAO;
	
	@Before
	public void setUp() throws Exception {
		assertNotNull(dataSource);
		assertNotNull(boardDAO);
	}
	
	@Test
	public void testInsertBoard() {
	    try {
	        BoardVO board = new BoardVO();
	        board.setTitle("test title");
	        board.setWriter("test writer");
	        board.setContent("test content");
	        int newId = boardDAO.insertBoard(board);
		    board.setBoardId(newId);
	        BoardVO resultBoard = boardDAO.getBoard(board.getBoardId());
	        assertNotNull(resultBoard);
	        assertEquals(board.getTitle(), resultBoard.getTitle());
	        assertEquals(board.getWriter(), resultBoard.getWriter());
	        assertEquals(board.getContent(), resultBoard.getContent());
		    System.out.println(resultBoard.getBoardId());
	    } catch (SQLException e) {
	        fail(e.getMessage());
	    }
	}
	
	@Test
	public void testGetBoardList() {
		try {
			List<BoardVO> boardList = boardDAO.getBoardList();
			assertNotNull(boardList);
			assertTrue(boardList.size() > 0);
		    System.out.println(boardList.get(boardList.size()-1).getBoardId());
		   // for (BoardVO board : boardList) {System.out.println(board.getBoardId()); }
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void testGetBoard() {
	    try {
	        BoardVO board = new BoardVO();
	        board.setTitle("test title");
	        board.setWriter("test writer");
	        board.setContent("test content");
	        int newId = boardDAO.insertBoard(board);
		    board.setBoardId(newId);
	        BoardVO resultBoard = boardDAO.getBoard(board.getBoardId());
	        assertNotNull(resultBoard);
	        assertEquals(board.getTitle(), resultBoard.getTitle());
	        assertEquals(board.getWriter(), resultBoard.getWriter());
	        assertEquals(board.getContent(), resultBoard.getContent());
	        int expectedBoardId = board.getBoardId();
	        int actualBoardId = resultBoard.getBoardId();
	        assertEquals(expectedBoardId, actualBoardId);
		    System.out.println(resultBoard.getBoardId());
	    } catch (SQLException e) {
	        fail(e.getMessage());
	    }
	}
	
	@Test
	public void testUpdateBoard() {
	  try {
	    BoardVO board = new BoardVO();
	    board.setTitle("test title");
	    board.setWriter("test writer");
	    board.setContent("test content");
	    int newId = boardDAO.insertBoard(board);
	    board.setBoardId(newId);
	    board.setTitle("updated title");
	    board.setContent("updated content");
	    boardDAO.updateBoard(board);
	    BoardVO resultBoard = boardDAO.getBoard(board.getBoardId());
	    assertNotNull(resultBoard);
	    assertEquals(board.getTitle(), resultBoard.getTitle());
	    assertEquals(board.getWriter(), resultBoard.getWriter());
	    assertEquals(board.getContent(), resultBoard.getContent());
	    System.out.println(resultBoard.getBoardId());
	  } catch (SQLException e) {
	    fail(e.getMessage());
	  }
	}
	@Test
	public void testDeleteBoard() {
	    try {
	        BoardVO board = new BoardVO();
	        board.setTitle("test title");
	        board.setWriter("test writer");
	        board.setContent("test content");
	        int newId = boardDAO.insertBoard(board);
	        board.setBoardId(newId);
	        System.out.println(board.getBoardId());
	        boardDAO.deleteBoard(board.getBoardId());
	        assertNull(boardDAO.getBoard(board.getBoardId()));
	    } catch (SQLException e) {
	        fail(e.getMessage());
	    }
	}
}