package com.myCommunity.login;

import org.apache.ibatis.annotations.Param;

public interface LoginService {
	
	LoginVo loginConfirm(@Param("id") String id, @Param("pwd") String pwd);

}
