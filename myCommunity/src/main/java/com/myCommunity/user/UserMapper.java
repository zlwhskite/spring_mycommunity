package com.myCommunity.user;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myCommunity.board.BoardVo;


@Mapper
public interface UserMapper {
	
	int save(UserVo userVo);
	
	void update(@Param("id") int id, @Param("userVo") UserVo userVo);
	
	void delete(@Param("id") int id, @Param("userVo") UserVo userdVo);
	
	int pwdModify(@Param("id") int id, @Param("userVo") UserVo userdVo, @Param("pwd") String pwd);
	
	UserVo findById(int id);
	
	UserVo findBynickName(String nickName);
	
	List<UserVo> findAll(); 
	
	UserVo findUserNickName(String email);
	UserVo findPwd(@Param("nickName") String nickName, @Param("email") String email);
	void resetPwd(@Param("id") int id, @Param("pwd") String pwd);

}
