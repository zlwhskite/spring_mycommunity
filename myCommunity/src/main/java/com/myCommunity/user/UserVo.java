package com.myCommunity.user;


public class UserVo {
	private int id;
	private String email;
	private String nickName;
	private String password;
	private String gender;
	private int auth;
	private String createTime;
	private String modifyTime;
	private String deleteTime;
	
	
	public UserVo() {}


	public UserVo(int id, String email, String nickName, String password, String gender, int auth, String createTime,
			String modifyTime, String deleteTime) {
		super();
		this.id = id;
		this.email = email;
		this.nickName = nickName;
		this.password = password;
		this.gender = gender;
		this.auth = auth;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.deleteTime = deleteTime;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getAuth() {
		return auth;
	}


	public void setAuth(int auth) {
		this.auth = auth;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getModifyTime() {
		return modifyTime;
	}


	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}


	public String getDeleteTime() {
		return deleteTime;
	}


	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}
	
}

/*
create table users (
 	id bigint generated by default as identity,
 	nickname varchar(50) not null,
	email varchar(50) not null,
	password varchar(255) not null,
	gender varchar(10) not null,
  	auth int not null default 0,
 	create_time varchar(50) not null,
 	modify_time varchar(50) null,
 	delete_time varchar(50) null,
 	primary key (id)
)
*/


