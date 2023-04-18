package com.myCommunity.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
	
	public List<CategoryVo> categoryList() {
		return categoryMapper.categoryList();
	}
	
	public CategoryVo findById(int categoryId) {
		return categoryMapper.findById(categoryId);
	}
	
	public int categoryEdit(String name1, String name2) {
		return categoryMapper.categoryEdit(name1, name2);
	}
	
	public int categoryOrderEdit(int categoryId, String categoryName) {
		return categoryMapper.categoryOrderEdit(categoryId, categoryName);
	}
}
