package com.myCommunity.search;

import java.util.ArrayList;
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
	public List<SearchVo> searchs(int startIndex, int pageSize, String division, String search) {
		return searchMapper.searchs(startIndex, pageSize, division, search);
	}
	@Override
	public List<SearchVo> searchCount(String division, String search) {
		return searchMapper.searchCount(division, search);
	}
	@Override
	public List<SearchVo> searchNickName(int startIndex, int pageSize, String nickName) {
		try {
			return searchMapper.searchNickName(startIndex, pageSize, nickName);
		}catch(Exception e) {
			return new ArrayList<SearchVo>();
		}
	}

}
