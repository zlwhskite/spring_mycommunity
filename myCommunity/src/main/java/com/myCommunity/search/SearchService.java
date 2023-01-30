package com.myCommunity.search;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SearchService {
//	List<SearchVo> allSearch(String search);
	List<SearchVo> searchs(int startIndex, int pageSize, String division, String search);
	List<SearchVo> searchCount(String division, String search);
}
