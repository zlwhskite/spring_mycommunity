package com.myCommunity.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginMapper loginMapper;

	@Override
	public LoginVo loginConfirm(String id, String pwd) {		
		return loginMapper.loginConfirm(id, pwd);
	}

}
