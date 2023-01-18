package com.myCommunity.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public void save(BoardVo boardVo) {
		boardMapper.save(boardVo);
	}

	@Override
	public void update(int id, BoardVo boardVo) {
		boardMapper.update(id, boardVo);
		
	}

	@Override
	public void delete(int id, BoardVo boardVo) {
		boardMapper.delete(id, boardVo);
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

}
