package com.myCommunity.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myCommunity.board.BoardVo;

@Mapper
public interface CommentMapper {
	
	List<CommentVo> commentList(int id);
	List<CommentVo> commentListdelete(int id);
	CommentVo findById(int id);
	void commentCreate(CommentVo commentVo);
	void commentModify(@Param("id") int id, @Param("commentVo") CommentVo commentVo);
	void commentDelete(@Param("id") int id, @Param("commentVo") CommentVo commentVo);
	void commentUserDelete(@Param("commentVo") CommentVo commentVo);
	
}
