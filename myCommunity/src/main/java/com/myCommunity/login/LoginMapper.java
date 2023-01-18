package com.myCommunity.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
	LoginVo loginConfirm(@Param("id") String id, @Param("pwd") String pwd);
}
