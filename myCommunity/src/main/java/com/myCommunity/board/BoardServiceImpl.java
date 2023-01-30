package com.myCommunity.board;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public void save(BoardVo boardVo) {
		boardMapper.save(boardVo);
	}

	@Override
	@Transactional
	public void update(int id, BoardVo boardVo) {
		boardMapper.update(id, boardVo);
		
	}

	@Override
	@Transactional
	public void delete(int id, BoardVo boardVo) {
		boardMapper.delete(id, boardVo);
	}
	
	@Override
	@Transactional
	public void userDelete(@Param("nickName") String nickName,  @Param("boardVo") BoardVo boardVo) {
		boardMapper.userDelete(nickName, boardVo);
	}
	
	
	@Override
	@Transactional
	public void hitsUp(@Param("id") int id) {
		boardMapper.hitsUp(id);
	}

	@Override
	public BoardVo findById(int id) {
		return boardMapper.findById(id);
	}
	
	@Override
	public List<BoardVo> findByName(String nickName) {
		return boardMapper.findByName(nickName);
	}


	@Override
	public List<BoardVo> findAll() {
		return boardMapper.findAll();
	}

	@Override
	public List<BoardVo> findAllHot() {
		return boardMapper.findAllHot();
	}

	@Override
	public List<BoardVo> findAllTravel() {
		return boardMapper.findAllTravel();
	}

	@Override
	public List<BoardVo> findAllHobby() {
		return boardMapper.findAllHobby();
	}

	@Override
	public List<BoardVo> findAllComputer() {
		return boardMapper.findAllComputer();
	}

	@Override
	public List<BoardVo> findAllStock() {
		return boardMapper.findAllStock();
	}

	@Override
	public List<BoardVo> findAllFree() {
		return boardMapper.findAllFree();
	}
	
	
	@Override
	public List<BoardVo> boardHits(String division) {
		return boardMapper.boardHits(division);
	}
	
	@Override
	public List<BoardVo> boardRecen(String division) {
		return boardMapper.boardRecen(division);
	}
	
	//언어변환
	public String dvchKE(String division) {
		if(division.equals("여행")) {
			division = "travel";
		}
		if(division.equals("취미")) {
			division = "hobby";
		}
		if(division.equals("컴퓨터")) {
			division = "computer";
		}
		if(division.equals("주식")) {
			division = "stock";
		}
		if(division.equals("자유게시판")) {
			division = "free";
		}
		return division;
	}
	public String dvchEK(String division) {
		if(division.equals("travel")) {
			division = "여행";
		}
		if(division.equals("hobby")) {
			division = "취미";
		}
		if(division.equals("computer")) {
			division = "컴퓨터";
		}
		if(division.equals("stock")) {
			division = "주식";
		}
		if(division.equals("free")) {
			division = "자유게시판";
		}
		return division;
	}

}
