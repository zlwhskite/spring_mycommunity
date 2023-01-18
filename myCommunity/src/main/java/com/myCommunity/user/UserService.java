package com.myCommunity.user;

import java.util.Optional;

import org.apache.ibatis.annotations.Param;

public interface UserService {

	int save(UserVo userVo);
	
	void update(@Param("id") int id, @Param("userVo") UserVo userVo);
	
	void delete(@Param("id") int id, @Param("userVo") UserVo userdVo);
	
	UserVo findById(int id);
	
	UserVo findBynickName(String nickName);
	
}
