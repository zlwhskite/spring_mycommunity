package com.myCommunity.attendance;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttendanceService {
	@Autowired
	AttendanceMapper attMapper;
	
	
	public int attenSave(AttendanceVo att) {
		int result = attMapper.attenSave(att);
		return result;
	}
	
	public AttendanceVo findById(int id, String attdate) {
		return attMapper.findById(id, attdate);
	}
	
	@Transactional
	public void update(int id, AttendanceVo att) {
		attMapper.update(id, att);
	}
	
	@Transactional
	public void delete(int id) {
		attMapper.delete(id);
	}
	
	public int todayUser(String attdate) {
		int result = attMapper.todayUser(attdate);
		return result;
	}
	
}
