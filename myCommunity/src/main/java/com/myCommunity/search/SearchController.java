package com.myCommunity.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myCommunity.board.BoardMapper;
import com.myCommunity.board.BoardServiceImpl;
import com.myCommunity.board.BoardVo;

@Controller
@RequestMapping("/boards")
public class SearchController {
	@Autowired
	SearchServiceImpl searchMapper;
	@Autowired
	BoardServiceImpl boardService;
	
	@GetMapping("/search")
	public String search(@RequestParam("division") String division, @RequestParam("search") String search, Model model) {
		
		if(division.equals("All")) {
			List<SearchVo> searchList = searchMapper.searchs(division, search);
			model.addAttribute("resultList", searchList);
			model.addAttribute("tit", division);
			
			return "search/searchResult";
		}

		List<SearchVo> searchList = searchMapper.searchs(division, search);
		model.addAttribute("resultList", searchList);
		model.addAttribute("tit", division);
	
		return "search/searchResult";

	}
	
	@GetMapping("/name/{nickName}")
	public String userBoard(@PathVariable("nickName") String nickName, Model model) {
		List<BoardVo> nickNameList = boardService.findByName(nickName);
		
		model.addAttribute("resultList", nickNameList);
		model.addAttribute("tit", nickName);
		
		return "search/searchResult";
	}
	
}
