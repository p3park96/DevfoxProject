package com.DevFox.biz.comment;

import java.util.List;

public interface CommentService {
	
	void insertComment(CommentVO vo);

	void updateLike(CommentVO vo);

	void updateBad(CommentVO vo);

	void updateComment(CommentVO vo);

	void deleteComment(CommentVO vo);

	CommentVO getComment(CommentVO vo);

	List<CommentVO> getCommentList(CommentVO vo);
}
