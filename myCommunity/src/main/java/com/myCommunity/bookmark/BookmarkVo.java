package com.myCommunity.bookmark;

public class BookmarkVo {
	private int id;
	private int userId;
	private int boardId;
	
	
	public BookmarkVo() {}
	public BookmarkVo(int id, int userId, int boardId) {
		super();
		this.id = id;
		this.userId = userId;
		this.boardId = boardId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

}

/*
create table bookmarks (
 	id bigint generated by default as identity,
 	user_id varchar(100) not null,
	board_id varchar(50) not null,
 	primary key (id)
)
*/
