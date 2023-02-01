package com.myCommunity.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myCommunity.board.BoardMapper;
import com.myCommunity.board.BoardServiceImpl;
import com.myCommunity.board.BoardVo;
import com.myCommunity.criteria.Criteria;
import com.myCommunity.criteria.CriteriaService;
import com.myCommunity.criteria.Pagination;

@Controller
@RequestMapping("/boards")
public class SearchController {
	@Autowired
	SearchServiceImpl searchMapper;
	@Autowired
	BoardServiceImpl boardService;
	@Autowired
	CriteriaService criteriaService;

	
	@GetMapping("/search")
	public String search(@RequestParam("division") String division, @RequestParam("search") String search, 
			@RequestParam(value="page", required=false, defaultValue = "1") int page, RedirectAttributes rttr, Model model) {	
		if(division.equals("All")) {
			List<SearchVo> countList = searchMapper.searchCount(division, search);
			if(countList.size() == 0) {
				rttr.addFlashAttribute("errm", "검색결과가 없습니다.");
				return "redirect:/boards";
			}
			int totalCount = countList.size();
			
			Pagination pn = new Pagination();
			Criteria pg = new Criteria();
			
			if(page <= 0) {
				page = 1;
			}
			
			pg.setPage(page);
			
			pn.setCriteria(pg);
			
			pn.setTotalCount(totalCount);

			if(pn.getEndPage() < page) {
				page = pn.getTotalPageCount();
				pg.setPage(page);
				
				pn.setCriteria(pg);
				
				pn.setTotalCount(totalCount);
			}
			
			int start = pn.getCriteria().getPageStart();
			int size = pn.getCriteria().getRecordSize();
			
			List<SearchVo> searchList = searchMapper.searchs(start, size, division, search);
			model.addAttribute("pagination", pn);
			model.addAttribute("resultList", searchList);
			model.addAttribute("countList", countList);
			model.addAttribute("search", search);
			model.addAttribute("tit", division);
			
			return "search/searchResult";
		}

		List<SearchVo> countList = searchMapper.searchCount(division, search);
		if(countList.size() == 0) {
			rttr.addFlashAttribute("errm", "검색결과가 없습니다.");
			return "redirect:/boards";
		}
		int totalCount = countList.size();
		
		
		Pagination pn = new Pagination();
		Criteria pg = new Criteria();
		
		if(page <= 0) {
			page = 1;
		}
		
		pg.setPage(page);
		
		pn.setCriteria(pg);
		
		pn.setTotalCount(totalCount);

		if(pn.getEndPage() < page) {
			page = pn.getTotalPageCount();
			pg.setPage(page);
			
			pn.setCriteria(pg);
			
			pn.setTotalCount(totalCount);
		}
		
		int start = pn.getCriteria().getPageStart();
		int size = pn.getCriteria().getRecordSize();
		
		List<SearchVo> searchList = searchMapper.searchs(start, size, division, search);
		model.addAttribute("pagination", pn);
		model.addAttribute("resultList", searchList);
		model.addAttribute("countList", countList);
		model.addAttribute("search", search);
		model.addAttribute("tit", division);
	
		return "search/searchResult";

	}
	
	@GetMapping("/name/{nickName}")
	public String userBoard(@PathVariable("nickName") String nickName, Model model) {
		List<BoardVo> nickNameList = boardService.findByName(nickName);
		
		model.addAttribute("resultList", nickNameList);
		model.addAttribute("countList", nickNameList);
		model.addAttribute("tit", nickName);
		
		return "search/searchResult";
	}
	
}
