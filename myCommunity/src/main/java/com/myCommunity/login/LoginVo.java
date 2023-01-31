package com.myCommunity.login;

public class LoginVo {
	private int id;
	private String nickName;
	private String password;
	private int auth;
	
	public LoginVo() {}
	public LoginVo(int id, String nickName, String password) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.password = password;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	
	
}
