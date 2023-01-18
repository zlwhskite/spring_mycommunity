package com.myCommunity.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myCommunity.board.BoardMapper;
import com.myCommunity.board.BoardVo;

@Controller
@RequestMapping("/boards")
public class SearchController {
	@Autowired
	SearchServiceImpl searchMapper;
	
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

}
