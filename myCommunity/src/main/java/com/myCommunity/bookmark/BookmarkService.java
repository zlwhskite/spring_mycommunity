package com.myCommunity.bookmark;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {
	@Autowired
	BookmarkMapper bookmarkMapper;
	
	public int bookmarkSave(BookmarkVo bm) {
		int result = bookmarkMapper.bookmarkSave(bm);
		
		return result;
	}
	public List<BookmarkVo> bookmarkList(int userId) {
		List<BookmarkVo> bookmarkList = bookmarkMapper.bookmarkList(userId);
		
		return bookmarkList;
	}
	
	public BookmarkVo bookmarkcheck(int userId, int boardId) {
		BookmarkVo bm = bookmarkMapper.bookmarkcheck(userId, boardId);
		
		return bm;
	}
	
	public void delete(int userId, int boardId) {
		bookmarkMapper.delete(userId, boardId);
	}
}
