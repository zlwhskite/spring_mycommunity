package com.myCommunity.search;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SearchService {
//	List<SearchVo> allSearch(String search);
	List<SearchVo> searchs(String division, String search);
}
