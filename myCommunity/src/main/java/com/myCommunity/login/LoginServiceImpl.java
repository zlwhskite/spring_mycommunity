package com.myCommunity.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginMapper loginMapper;

	@Override
	@Transactional
	public LoginVo loginConfirm(String id, String pwd) {		
		return loginMapper.loginConfirm(id, pwd);
	}

}
