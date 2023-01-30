package com.myCommunity.search;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myCommunity.board.BoardVo;
import com.myCommunity.user.UserVo;

@Mapper
public interface SearchMapper {

	List<SearchVo> searchs(@Param("startIndex")int startIndex, @Param("pageSize") int pageSize, @Param("division") String division, @Param("search") String search);
	List<SearchVo> searchCount(@Param("division") String division, @Param("search") String search);
	UserVo findBynickName(String nickName);
}
