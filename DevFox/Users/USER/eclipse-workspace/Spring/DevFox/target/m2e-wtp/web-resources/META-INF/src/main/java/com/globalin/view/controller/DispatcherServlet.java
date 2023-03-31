package com.globalin.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.globalin.biz.board.BoardVO;
import com.globalin.biz.board.impl.BoardDAO;
import com.globalin.biz.user.UserVO;
import com.globalin.biz.user.impl.UserDAO;

//@WebServlet(name = "action", urlPatterns = { "*.do" })

public class DispatcherServlet extends HttpServlet {
	
   private static final long serialVersionUID = 1L;

   private HandlerMapping handlerMapping;
   private ViewResolver viewResolver;
   
   @Override
   public void init() throws ServletException {

      handlerMapping = new HandlerMapping();
      viewResolver = new ViewResolver();
      viewResolver.setprefix("./");
      viewResolver.setsufix(".jsp");
      super.init();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      process(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      process(request, response);
   }

   protected void process(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // 1.Client�� ��û path ������ ������.
      String uri = request.getRequestURI();
      String path = uri.substring(uri.lastIndexOf("/"));
      System.out.println(path);

      // 2. Client�� ��û path�� ���� ������ �б� ó����.
      if (path.equals("/login.do")) {
         System.out.println("�α��� ó��..");
         // 1. ����ڰ� �Է��� ������ ����
         String id = request.getParameter("id");
         String password = request.getParameter("password");
         
         // 2. DB ���� ó��
         UserVO vo = new UserVO();
         vo.setId(id);
         vo.setPassword(password);
         
         UserDAO userDAO = new UserDAO();
         UserVO user = userDAO.getUser(vo);
         
         
         // 3. ȭ�� �׺���̼�

         if(user != null){
            System.out.println("�α��� ����");
            response.sendRedirect("getBoardList.do");
            
         }else{
            response.sendRedirect("login.jsp");
         }
         
      } else if (path.equals("/logout.do")) {
         System.out.println("�α׾ƿ� ó��..");
         
         HttpSession session = request.getSession();
         session.invalidate();
         response.sendRedirect("login.jsp");
   
         
         
      } else if (path.equals("/insertBoard.do")) {
         System.out.println("�� ��� ó��..");
         
         request.setCharacterEncoding("UTF-8");
         String title = request.getParameter("title");
         String writer = request.getParameter("writer");
         String content = request.getParameter("content");
         
         //DB
         BoardVO vo = new BoardVO();
         vo.setTitle(title);
         vo.setWriter(writer);
         vo.setContent(content);
         
         BoardDAO boardDAO = new BoardDAO();
         boardDAO.insertBoard(vo);
         
         
         //����ȭ�� ���
         response.sendRedirect("getBoardList.do");
         
         
         
      } else if (path.equals("/updateBoard.do")) {
         System.out.println("�� ���� ó��..");
//         request.setCharacterEncoding("UTF-8");
         String title = request.getParameter("title");
         String content = request.getParameter("content");
         String seq = request.getParameter("seq");
         
         //DB
         BoardVO vo = new BoardVO();
         vo.setTitle(title);
         vo.setContent(content);
         vo.setSeq(Integer.parseInt(seq));
         
         BoardDAO boardDAO = new BoardDAO();
         boardDAO.updateBoard(vo);
         
         //����ȭ�� ���
         response.sendRedirect("getBoardList.do");
         
         
      } else if (path.equals("/deleteBoard.do")) {
         System.out.println("�� ���� ó��..");
         
         String seq = request.getParameter("seq");
         
         //DB
         BoardVO vo = new BoardVO();
         vo.setSeq(Integer.parseInt(seq));
         
         BoardDAO boardDAO = new BoardDAO();
         boardDAO.deleteBoard(vo);
         
         
         //����ȭ�� ���
         response.sendRedirect("getBoardList.do");
         
         
         
      } else if (path.equals("/getBoard.do")) {
         System.out.println("�� �� ��ȸ ó��..");
         
         //�˻� �� �Խñ� ��ȣ�� ������.
         String seq = request.getParameter("seq");
         
         // DB����
         BoardVO vo = new BoardVO();
         vo.setSeq(Integer.parseInt(seq));
         BoardDAO boardDAO = new BoardDAO();
         BoardVO board = boardDAO.getBoard(vo);
         
         HttpSession session = request.getSession();
         session.setAttribute("board", board);
         response.sendRedirect("getBoard.jsp");
         
         
      } else if (path.equals("/getBoardList.do")) {
         System.out.println("�� ��� �˻� ó��..");
         
         // �����ͺ��̽� ����
         BoardVO vo = new BoardVO();
         BoardDAO boardDAO = new BoardDAO();
         List<BoardVO> boardList = boardDAO.getBoardList();
         
         HttpSession session = request.getSession();
         session.setAttribute("boardList", boardList);
         response.sendRedirect("getBoardList.jsp");
   
      }
   }
   
   Controller ctrl = handlerMapping.getController(path);
   
   String viewName = ctrl.handleRequest(request, response);
   String view = null;
   if(!viewName.contins(".do")) {
	   view =viewResolver.getView(viewName);
	   
	   
	   
   }
}