package com.myCommunity.criteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myCommunity.board.BoardVo;

@Service
public class CriteriaService {
	@Autowired
	CriteriaMapper pnm;
	
	
	public List<BoardVo> findAll(int startIndex, int pageSize, String division){
		return pnm.findAll(startIndex, pageSize, division);
	}
	public List<BoardVo> findHit(int startIndex, int pageSize, String division) {
		return pnm.findHit(startIndex, pageSize, division);
	}
	
	public List<BoardVo> findAllHit(int startIndex, int pageSize) {
		return pnm.findAllHit(startIndex, pageSize);
	}
	
	public int count(String division) {
		int result = pnm.count(division);
		return result;
	}
	
	public int allCount() {
		int result = pnm.allCount();
		return result;
	}
}
