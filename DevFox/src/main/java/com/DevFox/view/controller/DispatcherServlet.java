package com.DevFox.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.DevFox.biz.board.BoardVO;
import com.DevFox.biz.board.impl.BoardDAO;
import com.DevFox.biz.user.UserVO;
import com.DevFox.biz.user.impl.UserDAO;

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
      String uri = request.getRequestURI();
      String path = uri.substring(uri.lastIndexOf("/"));
      System.out.println(path);

      if (path.equals("/login.do")) {
         System.out.println("�α��� ó��..");
         String id = request.getParameter("id");
         String password = request.getParameter("password");
         
         UserVO vo = new UserVO();
         vo.setId(id);
         vo.setPassword(password);
         
         UserDAO userDAO = new UserDAO();
         UserVO user = userDAO.getUser(vo);
        

         if(user != null){
            response.sendRedirect("getBoardList.do");
            
         }else{
            response.sendRedirect("login.jsp");
         }
         
      }else if (path.equals("/logout.do")){
         
         HttpSession session = request.getSession();
         session.invalidate();
         response.sendRedirect("login.jsp");
   
      }else if (path.equals("/insertBoard.do")){
         
         request.setCharacterEncoding("UTF-8");
         String title = request.getParameter("title");
         String writer = request.getParameter("writer");
         String content = request.getParameter("content");
         
         BoardVO vo = new BoardVO();
         vo.setTitle(title);
         vo.setWriter(writer);
         vo.setContent(content);
         
         BoardDAO boardDAO = new BoardDAO();
         boardDAO.insertBoard(vo);
         
         response.sendRedirect("getBoardList.do");
         
      } else if (path.equals("/updateBoard.do")) {
         String title = request.getParameter("title");
         String content = request.getParameter("content");
         String seq = request.getParameter("seq");

         BoardVO vo = new BoardVO();
         vo.setTitle(title);
         vo.setContent(content);
         vo.setSeq(Integer.parseInt(seq));
         
         BoardDAO boardDAO = new BoardDAO();
         boardDAO.updateBoard(vo);

         response.sendRedirect("getBoardList.do");
         
         
      } else if (path.equals("/deleteBoard.do")) {

         String seq = request.getParameter("seq");
  
         BoardVO vo = new BoardVO();
         vo.setSeq(Integer.parseInt(seq));
      
         BoardDAO boardDAO = new BoardDAO();
         boardDAO.deleteBoard(vo);

         response.sendRedirect("getBoardList.do");
         
         
         
      } else if (path.equals("/getBoard.do")) {
         String seq = request.getParameter("seq");
         BoardVO vo = new BoardVO();
         vo.setSeq(Integer.parseInt(seq));
         BoardDAO boardDAO = new BoardDAO();
         BoardVO board = boardDAO.getBoard(vo);
         
         HttpSession session = request.getSession();
         session.setAttribute("board", board);
         response.sendRedirect("getBoard.jsp");
         
         
      } else if (path.equals("/getBoardList.do")) {
         BoardVO vo = new BoardVO();
         BoardDAO boardDAO = new BoardDAO();
         List<BoardVO> boardList = boardDAO.getBoardList(vo);
         
         HttpSession session = request.getSession();
         session.setAttribute("boardList", boardList);
         response.sendRedirect("getBoardList.jsp");
   
      }
   Controller ctrl = handlerMapping.getController(path);
   String viewName = ctrl.handleRequest(request, response);
   String view = null;
   if (!viewName.contains(".do")) {
      view = viewResolver.getView(viewName);
   }
   request.getRequestDispatcher(view).forward(request, response);
}
}