package com.DevFox.view.user;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DevFox.biz.user.UserService;
import com.DevFox.biz.user.UserVO;
import com.DevFox.biz.user.impl.UserDAO;

@Controller
@SessionAttributes("user")
public class UserController {
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 처리..");
		
		session.invalidate();
		
		return "user/login.jsp";
	}

	@Autowired
	private UserService userService;
	
	@RequestMapping("/writeUser.do")
	public String writeUser(UserVO vo) throws IOException {
		System.out.println("유저 작성 처리..");		
		return "userwriteUser.jsp";
	}
	
	@Controller
	public class LoginController{
		
		@RequestMapping(value="/login.do", method=RequestMethod.GET)
		public String loginView(UserVO vo) { // get 방식
			System.out.println("로그인 화면으로 이동 .......");
			vo.setId("admin");
			vo.setPassword("admin");
			
			return "user/login.jsp";
		}
		
		@RequestMapping(value="/login.do", method=RequestMethod.POST)
		public String login(UserVO vo, UserDAO userDAO, HttpSession session, RedirectAttributes redirectAttributes) { // post 방식

		    System.out.println("로그인 인증 처리 .......");

		    if(vo.getId()==null||vo.getId().equals("")) {
		        throw new IllegalArgumentException("아이디는 반드시 입력하셔야 합니다...");
		    }
		    
		    UserVO user = userDAO.getUser(vo);
		    
		    if (user.getId()!= null) {
		        session.setAttribute("userName", user.getName());
		        redirectAttributes.addFlashAttribute("userName", user.getName());
		        return "redirect:getBoardList.do";
		    } else {
		    	  System.out.println("로그인 실패!");
		    	  redirectAttributes.addFlashAttribute("errorMsg", "로그인에 실패하였습니다.");
		    	  return "redirect:login.do";
		    	}
		}

	}
	
	
	@RequestMapping(value="/signup.do", method=RequestMethod.GET)
	public String showSignupForm(Model model) {
	    model.addAttribute("user", new UserVO());
	    return "user/signupForm.jsp";
	}
	
	@RequestMapping(value="/insertUser.do")
	public String insertUser(UserVO vo, HttpSession session,UserDAO userDAO, RedirectAttributes redirectAttributes) throws IOException {
		System.out.println("유저 등록 처리..");
		userService.insertUser(vo);
		redirectAttributes.addFlashAttribute("userName", vo.getName());
		return "redirect:getBoardList.do";
	}


	@RequestMapping("/updateUser.do")
	public String updateUser(@ModelAttribute("user") UserVO vo) {
		
		System.out.println("유저 수정 처리..");

		userService.updateUser(vo);
		
		return "getUserList.do";
	}

	@RequestMapping("/deleteUser.do")
	public String deleteUser(UserVO vo) {
		
		System.out.println("유저 삭제 처리..");
		userService.deleteUser(vo);
		
		return "getUserList.do";
	}

	@RequestMapping("/getUser.do")
	public String getUser(UserVO vo, Model model) {
		
		System.out.println("유저 상세 조회 처리..");
		
		// Model 정보 저장
		model.addAttribute("user", userService.getUser(vo));
		
		return "user/getUser.jsp"; // View 이름을 리턴함
	}

	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}

	
}