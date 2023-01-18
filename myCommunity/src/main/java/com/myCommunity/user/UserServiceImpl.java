package com.myCommunity.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;

	@Override
	public int save(UserVo userVo) {
		return userMapper.save(userVo);
	}

	@Override
	public void update(int id, UserVo userVo) {
		userMapper.update(id, userVo);
	}

	@Override
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
	

}
