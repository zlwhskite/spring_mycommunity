package com.myCommunity.board;


public class BoardVo {
	private int id;
	private String title;
	private String userNickName;
	private String contents;
	private String division;
	private int hits;
	private String createTime;
	private String modifyTime;
	private String deleteTime;
	
	public BoardVo() {}
	
	public BoardVo(int id, String userNickName, String contents, String division, int hits, String createTime,
			String modifyTime, String deleteTime) {
		super();
		this.id = id;
		this.userNickName = userNickName;
		this.contents = contents;
		this.division = division;
		this.hits = hits;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
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

insert into boards (title, contents, user_nickname, division, create_time)
		values ('tesㄴㅇㄹt1', 'testtesttest', '잉어킹', '취미', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '취미', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt6', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst7', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄴㅊㄴㄹst8', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst9', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst10', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹㄴㅇㄹst11', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst12', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt13', 'testtesttest', '잉어킹', '취미', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt14', 'testtesttest', '잉어킹', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst7', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄴㅊㄴㄹst8', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst9', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst10', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹㄴㅇㄹst11', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst12', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst7', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄴㅊㄴㄹst8', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst9', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst10', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹㄴㅇㄹst11', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst12', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst7', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄴㅊㄴㄹst8', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst9', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst10', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹㄴㅇㄹst11', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst12', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst7', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄴㅊㄴㄹst8', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst9', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst10', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹㄴㅇㄹst11', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst12', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst7', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄴㅊㄴㄹst8', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst9', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst10', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹㄴㅇㄹst11', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst12', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst7', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄴㅊㄴㄹst8', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst9', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst10', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹㄴㅇㄹst11', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst12', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst7', 'testtesttest', '미뇽', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄴㅊㄴㄹst8', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst9', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst10', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹㄴㅇㄹst11', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00'),
		('teㄴㅇㄹst12', 'testtesttest', '팬텀', '취미', '2023-01-30 15:24:00')
		
		
	insert into boards (title, contents, user_nickname, division, create_time)
		values ('tesㄴㅇㄹt1', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '주식', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '주식', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '주식', '2023-01-30 15:24:00'),
		('tesㄴㅇㄹt2', 'testtesttest', '잉어킹', '자유게시판', '2023-01-30 15:24:00'),
		('teㅇㄹㄴst3', 'testtesttest', '팬텀', '자유게시판', '2023-01-30 15:24:00'),
		('teㄴㄴsㄴㅇㄹt4', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '미뇽', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00'),
		('teㅊㄴst5', 'testtesttest', '잠만보', '자유게시판', '2023-01-30 15:24:00')
		

		

*/