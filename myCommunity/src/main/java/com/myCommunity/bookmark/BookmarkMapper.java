package com.myCommunity.bookmark;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface BookmarkMapper {
	int bookmarkSave(BookmarkVo bookmark);
	List<BookmarkVo> bookmarkList(int userId);
	BookmarkVo bookmarkcheck(@Param("userId") int userId, @Param("boardId") int boardId);
	void delete(@Param("userId") int userId, @Param("boardId") int boardId);
}
