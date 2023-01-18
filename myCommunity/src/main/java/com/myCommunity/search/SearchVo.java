package com.myCommunity.search;

public class SearchVo {
	private int id;
	private String title;
	private String userNickName;
	private String contents;
	private String division;
	private int hits;
	private String createTime;
	private String modifyTime;
	private String deleteTime;
	
	public SearchVo() {}
	
	public SearchVo(int id, String userNickName, String contents, String division, int hits, String createTime,
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
	
	public SearchVo(int id, String userNickName, String contents, String division, int hits,
			String modifyTime, String deleteTime) {
		super();
		this.id = id;
		this.userNickName = userNickName;
		this.contents = contents;
		this.division = division;
		this.hits = hits;
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


//<select id="allSearch" resultType="SearchVo">
//SELECT id, title, contents, user_nickname, division, hits, modify_time, delete_time
//FROM boards
//WHERE delete_time is null
//<if test="search != null and search != ''">
//AND title LIKE '%' || #{search} || '%'
//</if>
//order by hits DESC
//</select>
//



//<select id="searchs" resultType="SearchVo">
//SELECT id, title, contents, user_nickname, division, hits, modify_time, delete_time
//FROM boards
//WHERE division=#{division}
//AND
//delete_time is null
//<if test="search != null and search != ''">
//AND title LIKE '%' || #{search} || '%'
//</if>
//order by hits DESC
//</select>
//

