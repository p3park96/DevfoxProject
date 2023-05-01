package com.DevFox.view.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.DevFox.biz.comment.CommentService;
import com.DevFox.biz.comment.CommentVO;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/writeComment.do", method = RequestMethod.POST)
	public String insertComment(CommentVO vo, Model model) {
		commentService.insertComment(vo);
		model.addAttribute("commentList", commentService.getCommentList(vo));
		return "redirect:getBoard.do?seq=" + vo.getBoardNo();
	}
	
	@RequestMapping(value = "/deleteComment.do", method = RequestMethod.POST)
	public String deleteComment(CommentVO vo, Model model) {
		commentService.deleteComment(vo);
		model.addAttribute("commentList", commentService.getCommentList(vo));
		return "redirect:getBoard.do?seq=" + vo.getBoardNo();
	}
	
	@RequestMapping(value = "/updateComment.do", method = RequestMethod.POST)
	public String updateComment(CommentVO vo, Model model) {
		commentService.updateComment(vo);
		model.addAttribute("commentList", commentService.getCommentList(vo));
		return "redirect:getBoard.do?seq=" + vo.getBoardNo();
	}
	
	@RequestMapping(value = "/updateLike.do", method = RequestMethod.POST)
	public String updateLike(CommentVO vo, Model model) {
		commentService.updateLike(vo);
		model.addAttribute("commentList", commentService.getCommentList(vo));
		return "redirect:getBoard.do?seq=" + vo.getBoardNo();
	}
	
	@RequestMapping(value = "/updateBad.do", method = RequestMethod.POST)
	public String updateBad(CommentVO vo, Model model) {
		commentService.updateBad(vo);
		model.addAttribute("commentList", commentService.getCommentList(vo));
		return "redirect:getBoard.do?seq=" + vo.getBoardNo();
	}
	
	@ModelAttribute("commentVO")
	public CommentVO commentVO() {
		return new CommentVO();
	}
	
	@ModelAttribute("commentList")
	public List<CommentVO> commentList(CommentVO vo) {
		return commentService.getCommentList(vo);
	}

}