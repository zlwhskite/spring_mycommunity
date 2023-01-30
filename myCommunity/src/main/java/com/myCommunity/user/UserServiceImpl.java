package com.myCommunity.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;

	@Override
	public int save(UserVo userVo) {
		return userMapper.save(userVo);
	}

	@Override
	@Transactional
	public void update(int id, UserVo userVo) {
		userMapper.update(id, userVo);
	}
	
	@Override
	@Transactional
	public int pwdModify(@Param("id") int id, @Param("userVo") UserVo userdVo, @Param("pwd") String pwd) {
		int result = userMapper.pwdModify(id, userdVo, pwd);
		
		
		return result;
		
	}
	

	@Override
	@Transactional
	public void delete(int id, UserVo userdVo) {
		userMapper.delete(id, userdVo);
	}

	@Override
	public UserVo findById(int id) {
		return userMapper.findById(id);
	}
	
	@Override
	public UserVo findBynickName(String nickName) {
		UserVo user = userMapper.findBynickName(nickName);
		return user;
	}
	
	@Override
	public List<UserVo> findAll() {
		return userMapper.findAll();
	}
	
	@Override
	public UserVo findUserNickName(String email) {
		UserVo user = userMapper.findUserNickName(email);
		return user;
	}
	
	@Override
	public UserVo findPwd(String nickName, String email) {
		UserVo user = userMapper.findPwd(nickName, email);
		return user;
	}
	@Override
	public void resetPwd(int id, String pwd) {
		userMapper.resetPwd(id, pwd);
	}
	

}
