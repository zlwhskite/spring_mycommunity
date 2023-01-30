package com.myCommunity.user;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

public interface UserService {

	int save(UserVo userVo);
	
	void update(@Param("id") int id, @Param("userVo") UserVo userVo);
	
	void delete(@Param("id") int id, @Param("userVo") UserVo userdVo);
	
	int pwdModify(@Param("id") int id, @Param("userVo") UserVo userdVo, @Param("pwd") String pwd);
	
	UserVo findById(int id);
	
	UserVo findBynickName(String nickName);
	
	List<UserVo> findAll(); 
	
	UserVo findUserNickName(String email);	
	UserVo findPwd(String nickName, String email);
	void resetPwd(int id, String pwd);

}
