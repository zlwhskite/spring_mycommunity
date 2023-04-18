package com.myCommunity.category;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.myCommunity.comment.CommentVo;

@Mapper
public interface CategoryMapper {
	List<CategoryVo> categoryList();
	CategoryVo findById(int categoryId);
	int categoryEdit(@Param("name1") String name1, @Param("name2") String name2);
	int categoryOrderEdit(@Param("categoryId") int categoryId, @Param("categoryName") String categoryName);
}
