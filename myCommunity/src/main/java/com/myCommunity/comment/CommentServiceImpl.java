package com.myCommunity.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<CommentVo> commentList(int id) {
		return commentMapper.commentList(id);
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
	public void commentModify(int id, CommentVo commentVo) {
		commentMapper.commentModify(id, commentVo);
	}

	@Override
	public void commentDelete(int id, CommentVo commentVo) {
		commentMapper.commentDelete(id, commentVo);
	}

}
