package com.myCommunity.attendance;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myCommunity.user.UserVo;

@Mapper
public interface AttendanceMapper {
	int attenSave(AttendanceVo att);
	AttendanceVo findById(@Param("userId")int id, @Param("attendanceDate") String attdate);
	void update(@Param("userId") int id, @Param("attVo") AttendanceVo att);
	void delete(@Param("userId") int id);
	int todayUser(@Param("attendanceDate") String attdate);
}
