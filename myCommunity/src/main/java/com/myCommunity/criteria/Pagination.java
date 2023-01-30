package com.myCommunity.criteria;

public class Pagination {
	private Criteria criteria;
	//총 게시글의 수
	private int totalCount;
	//전체 페이지 수
	private int totalPageCount;
	//시작 페이지 번호
	private int startPage;
	//끝 페이지 번호
	private int endPage;
	//이전 버튼
	private boolean prev;
	//다음번튼
	private boolean next;
	
	
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public Criteria getCriteria() {
		return criteria;
	}
	public void setCriteria(Criteria pagination) {
		this.criteria = pagination;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		page();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	private void page() {
		//화면에 보여질 마지막 페이지 번호
		endPage = (int)(Math.ceil(criteria.getPage() / (double)criteria.getPageSize()) * criteria.getPageSize());
		//화면에 보여질 시작 페이지 번호
		startPage = (endPage - criteria.getPageSize()) + 1;
		//체크
		if(startPage <= 0) {
			startPage = 1;
		}
		//전체 마지막 페이지
		totalPageCount = (int)(Math.ceil(totalCount / (double)criteria.getRecordSize()));
		//체크
		if(totalPageCount < endPage) {
			endPage = totalPageCount;
		}

		prev = startPage != 1;
		next = (endPage * criteria.getRecordSize()) < totalCount;
	}
}
