package com.myCommunity.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	AdminMapper adminMapper;
	
	public List<AdminVo> userList() {
		return adminMapper.userList();
	}
	public List<AdminVo> boardList() {
		return adminMapper.boardList();
	}
	public List<AdminVo> todayUserList(String date) {
		return adminMapper.todayUserList(date);
	}
	public List<AdminVo> todayBoardList(String date) {
		return adminMapper.todayBoardList(date);
	}
	
}
