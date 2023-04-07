package com.DevFox.biz.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DevFox.biz.comment.CommentService;
import com.DevFox.biz.comment.CommentVO;
import com.DevFox.biz.common.Log4jAdvice;
import com.DevFox.biz.common.LogAdvice;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	private Log4jAdvice log;
	@Autowired
	private CommentDAO commentDAO;

	@Override
	public void insertComment(CommentVO vo) {
		commentDAO.insertComment(vo);
	}


	@Override
	public void updateLike(CommentVO vo) {
		commentDAO.updateLike(vo);
	}

	@Override
	public void updateBad(CommentVO vo) {
		commentDAO.updateBad(vo);
	}

	@Override
	public void updateComment(CommentVO vo) {
		commentDAO.updateComment(vo);
	}

	@Override
	public void deleteComment(CommentVO vo) {
		commentDAO.deleteComment(vo);
	}

	@Override
	public CommentVO getComment(CommentVO vo) {
		return commentDAO.getComment(vo);
	}

	@Override
	public List<CommentVO> getCommentList(CommentVO vo) {
		return commentDAO.getCommentList(vo);
	}
}
