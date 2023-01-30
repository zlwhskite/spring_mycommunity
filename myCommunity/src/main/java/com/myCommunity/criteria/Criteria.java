package com.myCommunity.criteria;

public class Criteria {
	//현재 페이지 번호
	private int page; 
	
	//한페이지에 보여중 게시글의 수
	private int recordSize;
	
	//화면 하단에 출력할 페이지 사이즈
	private int pageSize;
	

	public Criteria() {
		this.page = 1;
		this.recordSize = 8;
		this.pageSize = 5;  //1 ~ 5  /  6 ~ 10  / 11 ~ 15 
	}
	
	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}



	public int getRecordSize() {
		return recordSize;
	}



	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	//현재 페이지의 게시글의 시작번호
	public int getPageStart() {            //1    2    3
		return (page - 1) * recordSize;    //0~   10~  20~   ->  limit ${getPageStart}, #{recordSize}
	}
}
