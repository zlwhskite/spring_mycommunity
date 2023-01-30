package com.myCommunity.comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CommentService {
	
	List<CommentVo> commentList(int id);
	List<CommentVo> commentListdelete(int id);
	CommentVo findById(int id);
	void commentCreate(CommentVo commentVo);
	void commentModify(int id, CommentVo CommentVo);
	void commentDelete(int id, CommentVo CommentVo);
	void commentUserDelete(CommentVo commentVo);

}
