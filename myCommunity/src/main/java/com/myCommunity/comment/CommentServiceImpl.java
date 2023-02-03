package com.myCommunity.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<CommentVo> commentList(int id) {
		return commentMapper.commentList(id);
	}
	
	@Override
	public 	List<CommentVo> replyList(int id) {
		return commentMapper.replyList(id);
	}
	
	@Override
	public int commentsCount(int id) {
		int result = commentMapper.commentsCount(id);
		return result;
	}
	
	@Override
	public CommentVo findById(int id) {
		CommentVo commentVo = commentMapper.findById(id);
		return commentVo;
	}
	
	@Override
	public void commentCreate(CommentVo commentVo) {
		commentMapper.commentCreate(commentVo);
	}
	
	@Override
	public void replyCreate(CommentVo commentVo) {
		commentMapper.replyCreate(commentVo);
	}

	@Override
	@Transactional
	public void commentModify(int id, CommentVo commentVo) {
		commentMapper.commentModify(id, commentVo);
	}

	@Override
	@Transactional
	public void commentDelete(int id, CommentVo commentVo) {
		commentMapper.commentDelete(id, commentVo);
	}
	
	@Override
	@Transactional
	public void commentUserDelete(CommentVo commentVo) {
		commentMapper.commentUserDelete(commentVo);
	}

	@Override
	public List<CommentVo> commentListdelete(int id) {
		return commentMapper.commentListdelete(id);
	}
	
}
