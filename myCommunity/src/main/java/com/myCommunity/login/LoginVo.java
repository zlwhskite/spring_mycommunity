package com.myCommunity.login;

public class LoginVo {
	private String nickName;
	private String password;
	
	public LoginVo() {}
	public LoginVo(String nickName, String password) {
		super();
		this.nickName = nickName;
		this.password = password;
	}
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
