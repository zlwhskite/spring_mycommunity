package com.myCommunity.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myCommunity.comment.CommentServiceImpl;
import com.myCommunity.comment.CommentVo;
import com.myCommunity.user.UserVo;


@Controller
@RequestMapping("/boards")
public class BoardController {
	@Autowired
	private BoardServiceImpl boardService;
	@Autowired
	CommentServiceImpl commentService;

	
	@RequestMapping
	public String index(Model model) {
		List<BoardVo> boardList = boardService.findAll();
		List<BoardVo> travelList = boardService.findAllTravel();
		List<BoardVo> hobbyList = boardService.findAllHobby();
		List<BoardVo> computerList = boardService.findAllComputer();
		List<BoardVo> stockList = boardService.findAllStock();
		List<BoardVo> freeList = boardService.findAllFree();
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("travelList", travelList);
		model.addAttribute("hobbyList", hobbyList);
		model.addAttribute("computerList", computerList);
		model.addAttribute("stockList", stockList);
		model.addAttribute("freeList", freeList);
	
		return "board/index";
	}
	
	
	@GetMapping("/hot")
	public String hotBoard(Model model) {
		List<BoardVo> hotList = boardService.findAllHot();
		model.addAttribute("boardList", hotList);
		model.addAttribute("tit", "인기 글");
		model.addAttribute("enDivision", "hot");
		
		return "board/posts";
	}
	@GetMapping("/travel")
	public String travelBoard(@RequestParam(defaultValue="최신순") String srt, Model model) {
		if(srt.equals("인기순")) {
			List<BoardVo> stockList = boardService.boardHits("여행");
			model.addAttribute("boardList", stockList);
			model.addAttribute("tit", "여행");
			model.addAttribute("enDivision", "travel");
			model.addAttribute("srt", "인기순");
			return "board/posts";
		}
		List<BoardVo> travelList = boardService.findAllTravel();
		model.addAttribute("boardList", travelList);
		model.addAttribute("tit", "여행");
		model.addAttribute("enDivision", "travel");
		return "board/posts";
	}
	@GetMapping("/hobby")
	public String hobbyBoard(Model model) {
		List<BoardVo> hobbyList = boardService.findAllHobby();
		model.addAttribute("boardList", hobbyList);
		model.addAttribute("tit", "취미");
		model.addAttribute("enDivision", "hobby");
		return "board/posts";
	}
	@GetMapping("/computer")
	public String computerBoard(Model model) {
		List<BoardVo> computerList = boardService.findAllComputer();
		model.addAttribute("boardList", computerList);
		model.addAttribute("tit", "컴퓨터");
		model.addAttribute("enDivision", "computer");
		return "board/posts";
	}
	@GetMapping("/stock")
	public String stockBoard(Model model) {
		List<BoardVo> stockList = boardService.findAllStock();
		model.addAttribute("boardList", stockList);
		model.addAttribute("tit", "주식");
		model.addAttribute("enDivision", "stock");
		return "board/posts";
	}
	@GetMapping("/free")
	public String freeBoard(Model model) {
		List<BoardVo> freeList = boardService.findAllFree();
		model.addAttribute("boardList", freeList);
		model.addAttribute("tit", "자유게시판");
		model.addAttribute("enDivision", "free");
		return "board/posts";
	}
	
	@GetMapping("/{nickName}")
	public String userBoard(@PathVariable("nickName") String nickName, Model model) {
		List<BoardVo> nickNameList = boardService.findByName(nickName);
		
		model.addAttribute("resultList", nickNameList);
		model.addAttribute("tit", nickName);
		
		return "search/searchResult";
	}
	
	@GetMapping("/create")
	public String createPost() {
		return "board/createPost";
	}
	
	@PostMapping
	public String savePost(@ModelAttribute("board") BoardVo boardVo) {
		boardVo.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		boardVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		boardService.save(boardVo);
		
		if(boardVo.getDivision().equals("여행")) {
			boardVo.setDivision("travel");
		}
		if(boardVo.getDivision().equals("취미")) {
			boardVo.setDivision("hobby");
		}
		if(boardVo.getDivision().equals("컴퓨터")) {
			boardVo.setDivision("computer");
		}
		if(boardVo.getDivision().equals("주식")) {
			boardVo.setDivision("stock");
		}
		if(boardVo.getDivision().equals("자유게시판")) {
			boardVo.setDivision("free");
		}
		
		return "redirect:/boards/"+boardVo.getDivision();
	}
	
	@GetMapping("/{division}/{id}")
	public String showPost(@PathVariable("id") String id, Model model) {
		int boardId = Integer.parseInt(id);
		
		BoardVo boardVo = boardService.findById(boardId);
		List<CommentVo> commentList = commentService.commentList(boardId);
		
		if(boardVo.getDivision().equals("여행")) {
			model.addAttribute("tit", boardVo.getDivision());
			model.addAttribute("endivision", "travel");
		}
		if(boardVo.getDivision().equals("취미")) {
			model.addAttribute("tit", boardVo.getDivision());
			model.addAttribute("endivision", "hobby");
		}
		if(boardVo.getDivision().equals("컴퓨터")) {
			model.addAttribute("tit", boardVo.getDivision());
			model.addAttribute("endivision", "computer");
		}
		if(boardVo.getDivision().equals("주식")) {
			model.addAttribute("tit", boardVo.getDivision());
			model.addAttribute("endivision", "stock");
		}
		if(boardVo.getDivision().equals("자유게시판")) {
			model.addAttribute("tit", boardVo.getDivision());
			model.addAttribute("endivision", "free");
		}
		
		
		model.addAttribute("board", boardVo);
		model.addAttribute("commentList", commentList);
		
		return "board/showPost";
	}
	
	@GetMapping("/{division}/{id}/edit")
	public String editPost(@PathVariable("id") String id, Model model) {
		int boardId = Integer.parseInt(id);
		BoardVo boardVo = boardService.findById(boardId);
		
		if(boardVo.getDivision().equals("여행")) {
			model.addAttribute("tit", boardVo.getDivision());
		}
		if(boardVo.getDivision().equals("취미")) {
			model.addAttribute("tit", boardVo.getDivision());
		}
		if(boardVo.getDivision().equals("컴퓨터")) {
			model.addAttribute("tit", boardVo.getDivision());
		}
		if(boardVo.getDivision().equals("주식")) {
			model.addAttribute("tit", boardVo.getDivision());
		}
		if(boardVo.getDivision().equals("자유게시판")) {
			model.addAttribute("tit", boardVo.getDivision());
		}
		
		model.addAttribute("board", boardVo);
		
		return "board/editPost";
	}
	
	@PatchMapping("/{id}")
	public String updatePost(@ModelAttribute("board") BoardVo boardVo, @PathVariable("id") String id) {
		int boardId = Integer.parseInt(id);
		boardVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				
		boardService.update(boardId, boardVo);
		
		String select = "";
		
		if(boardVo.getDivision().equals("여행")) {
			boardVo.setDivision("travel");
			select = "travel";
		}
		if(boardVo.getDivision().equals("취미")) {
			boardVo.setDivision("hobby");
			select = "hobby";
		}
		if(boardVo.getDivision().equals("컴퓨터")) {
			boardVo.setDivision("computer");
			select = "computer";
		}
		if(boardVo.getDivision().equals("주식")) {
			boardVo.setDivision("stock");
			select = "stock";
		}
		if(boardVo.getDivision().equals("자유게시판")) {
			boardVo.setDivision("free");
			select = "free";
		}
		
		return "redirect:/boards/"+ select + "/" + boardVo.getId();
	}
	
	@DeleteMapping("/{id}")
	public String deletePost(@ModelAttribute("board") BoardVo boardVo, @PathVariable("id") String id) {
		int boardId = Integer.parseInt(id);
		boardVo.setDeleteTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		boardService.delete(boardId, boardVo);
		
		return "redirect:/boards";
	}
	
	
	@GetMapping("/srt")
	public String boardSort(@RequestParam("enDivision") String division, @RequestParam("srt") String sort, Model model) {
		if(sort.equals("최신순")) {
			if(division.equals("travel")) {
				List<BoardVo> travelList = boardService.findAllTravel();
				model.addAttribute("boardList", travelList);
				model.addAttribute("tit", "여행");
				model.addAttribute("enDivision", "travel");
				model.addAttribute("srt", "최신순");
				
				return "board/posts";
			}
			if(division.equals("hobby")) {
				List<BoardVo> hobbyList = boardService.findAllHobby();
				model.addAttribute("boardList", hobbyList);
				model.addAttribute("tit", "취미");
				model.addAttribute("enDivision", "hobby");
				model.addAttribute("srt", "최신순");
				return "board/posts";
			}
			if(division.equals("computer")) {
				List<BoardVo> computerList = boardService.findAllComputer();
				model.addAttribute("boardList", computerList);
				model.addAttribute("tit", "컴퓨터");
				model.addAttribute("enDivision", "computer");
				model.addAttribute("srt", "최신순");
				return "board/posts";
			}
			if(division.equals("stock")) {
				List<BoardVo> stockList = boardService.findAllStock();
				model.addAttribute("boardList", stockList);
				model.addAttribute("tit", "주식");
				model.addAttribute("enDivision", "stock");
				model.addAttribute("srt", "최신순");
				return "board/posts";
			}
			if(division.equals("free")) {
				List<BoardVo> freeList = boardService.findAllFree();
				model.addAttribute("boardList", freeList);
				model.addAttribute("tit", "자유게시판");
				model.addAttribute("enDivision", "free");
				model.addAttribute("srt", "최신순");
				return "board/posts";
			}
		}
		
		if(sort.equals("인기순")) {
			if(division.equals("travel")) {
				List<BoardVo> travelList = boardService.boardHits("여행");
				model.addAttribute("boardList", travelList);
				model.addAttribute("tit", "여행");
				model.addAttribute("enDivision", "travel");
				model.addAttribute("srt", "인기순");
				return "board/posts";
			}
			if(division.equals("hobby")) {
				List<BoardVo> hobbyList = boardService.boardHits("취미");
				model.addAttribute("boardList", hobbyList);
				model.addAttribute("tit", "취미");
				model.addAttribute("enDivision", "hobby");
				model.addAttribute("srt", "인기순");
				return "board/posts";
			}
			if(division.equals("computer")) {
				List<BoardVo> computerList = boardService.boardHits("컴퓨터");
				model.addAttribute("boardList", computerList);
				model.addAttribute("tit", "컴퓨터");
				model.addAttribute("enDivision", "computer");
				model.addAttribute("srt", "인기순");
				return "board/posts";
			}
			if(division.equals("stock")) {
				List<BoardVo> stockList = boardService.boardHits("주식");
				model.addAttribute("boardList", stockList);
				model.addAttribute("tit", "주식");
				model.addAttribute("enDivision", "stock");
				model.addAttribute("srt", "인기순");
				return "board/posts";
			}
			if(division.equals("free")) {
				List<BoardVo> freeList = boardService.boardHits("자유게시판");
				model.addAttribute("boardList", freeList);
				model.addAttribute("tit", "자유게시판");
				model.addAttribute("enDivision", "free");
				model.addAttribute("srt", "인기순");
				return "board/posts";
			}
		}
		
		return "redirect:/boards";
	}
}
