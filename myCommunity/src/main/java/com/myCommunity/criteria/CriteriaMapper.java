package com.myCommunity.criteria;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myCommunity.board.BoardVo;

@Mapper
public interface CriteriaMapper {
	List<BoardVo> findAll(@Param("startIndex")int startIndex, @Param("pageSize") int pageSize, @Param("division") String division);
	List<BoardVo> findHit(@Param("startIndex")int startIndex, @Param("pageSize") int pageSize, @Param("division") String division);
	List<BoardVo> findAllHit(@Param("startIndex")int startIndex, @Param("pageSize") int pageSize);
	int count(String division);
	int allCount();
}
