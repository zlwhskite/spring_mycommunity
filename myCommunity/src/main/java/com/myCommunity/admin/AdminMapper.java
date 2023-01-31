package com.myCommunity.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface AdminMapper {
	List<AdminVo> userList();
	List<AdminVo> boardList();
	List<AdminVo> todayUserList(String date);
	List<AdminVo> todayBoardList(String date);
}
