package com.myCommunity.search;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myCommunity.board.BoardVo;
import com.myCommunity.user.UserVo;

@Mapper
public interface SearchMapper {
	List<SearchVo> allSearch(@Param("search") String search);
	List<SearchVo> searchs(@Param("division") String division, @Param("search") String search);
	
	UserVo findBynickName(String nickName);
}
