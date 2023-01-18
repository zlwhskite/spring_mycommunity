package com.myCommunity.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	SearchMapper searchMapper;
	
//	@Override
//	public List<SearchVo> allSearch(String search) {
//		return searchMapper.allSearch(search);
//	}

	@Override
	public List<SearchVo> searchs(String division, String search) {
		return searchMapper.searchs(division, search);
	}

}
