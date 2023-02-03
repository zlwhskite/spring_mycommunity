package com.myCommunity.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface BoardMapper {
	
	void save(BoardVo boardVo);
	
	void update(@Param("id") int id, @Param("boardVo") BoardVo boardVo);
	
	void delete(@Param("id") int id, @Param("boardVo") BoardVo boardVo);
	
	void userDelete(@Param("nickName") String nickName,  @Param("boardVo") BoardVo boardVo);
	
	void commentsCount(@Param("id") int id, @Param("cnt") int cnt);
	
	void hitsUp(@Param("id") int id);
	
	BoardVo findById(int id);
	
	List<BoardVo> findByName(String nickName);
	
	List<BoardVo> findAll(); 
	List<BoardVo> findAllHot();
	List<BoardVo> findAllTravel(); 
	List<BoardVo> findAllHobby(); 
	List<BoardVo> findAllComputer(); 
	List<BoardVo> findAllStock(); 
	List<BoardVo> findAllWorkout();
	List<BoardVo> findAllFree();
	List<BoardVo> boardHits(String division);
	List<BoardVo> boardRecen(String division);
}
