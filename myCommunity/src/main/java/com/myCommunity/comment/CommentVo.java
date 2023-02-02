package com.myCommunity.comment;

public class CommentVo {
	private int id;
	private int boardId;
	private String userNickName;
	private String contents;
	private int groupId;
	private int dep;
	private String createTime;
	private String modifyTime;
	private String deleteTime;
	
	public CommentVo() {}
	


	public CommentVo(int id, int boardId, String userNickName, String contents, int groupId, int dep, String createTime,
			String modifyTime, String deleteTime) {
		super();
		this.id = id;
		this.boardId = boardId;
		this.userNickName = userNickName;
		this.contents = contents;
		this.groupId = groupId;
		this.dep = dep;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.deleteTime = deleteTime;
	}


	

	public int getGroupId() {
		return groupId;
	}



	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}



	public int getDep() {
		return dep;
	}



	public void setDep(int dep) {
		this.dep = dep;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	

}
