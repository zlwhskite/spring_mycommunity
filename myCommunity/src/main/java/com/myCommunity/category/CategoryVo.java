package com.myCommunity.category;

public class CategoryVo {
	private int id;
	private String categoryName;
	
	public CategoryVo() {}
	public CategoryVo(int id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
