package com.myCommunity.user;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {
	
	int save(UserVo userVo);
	
	void update(@Param("id") int id, @Param("userVo") UserVo userVo);
	
	void delete(@Param("id") int id, @Param("userVo") UserVo userdVo);
	
	UserVo findById(int id);
	
	UserVo findBynickName(String nickName);

}
