package com.myCommunity.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myCommunity.comment.CommentServiceImpl;
import com.myCommunity.comment.CommentVo;
import com.myCommunity.login.LoginVo;
import com.myCommunity.user.UserVo;


@Controller
@RequestMapping("/boards")
public class BoardController {
	@Autowired
	private BoardServiceImpl boardService;
	@Autowired
	CommentServiceImpl commentService;

	
	@RequestMapping
	public String index(HttpServletRequest request, Model model) {
		List<BoardVo> boardList = boardService.findAll();
		List<BoardVo> travelList = boardService.findAllTravel();
		List<BoardVo> hobbyList = boardService.findAllHobby();
		List<BoardVo> computerList = boardService.findAllComputer();
		List<BoardVo> stockList = boardService.findAllStock();
		List<BoardVo> freeList = boardService.findAllFree();
		
		HttpSession session = request.getSession(false);
		
		if (session == null) {

			model.addAttribute("boardList", boardList);
			model.addAttribute("travelList", travelList);
			model.addAttribute("hobbyList", hobbyList);
			model.addAttribute("computerList", computerList);
			model.addAttribute("stockList", stockList);
			model.addAttribute("freeList", freeList);
			
			return "board/index";
		}
		
		LoginVo loginUser = (LoginVo)session.getAttribute("user");

		if (loginUser == null) {
			
			model.addAttribute("boardList", boardList);
			model.addAttribute("travelList", travelList);
			model.addAttribute("hobbyList", hobbyList);
			model.addAttribute("computerList", computerList);
			model.addAttribute("stockList", stockList);
			model.addAttribute("freeList", freeList);
			
			return "board/index";
		}

		model.addAttribute("boardList", boardList);
		model.addAttribute("travelList", travelList);
		model.addAttribute("hobbyList", hobbyList);
		model.addAttribute("computerList", computerList);
		model.addAttribute("stockList", stockList);
		model.addAttribute("freeList", freeList);
		
		model.addAttribute("user", loginUser);
	
		return "board/index";
	}
	
	@GetMapping("/{division}")
	public String divisionBoard(@PathVariable("division") String division, Model model) {
		if(division.equals("hot")) {
			List<BoardVo> boardList = boardService.findAllHot();
			model.addAttribute("boardList", boardList);
			model.addAttribute("tit", "인기 글");
			model.addAttribute("enDivision", "hot");
			
			return "board/posts";
		}
		
		String endivision = "";
		
		if(division.equals("travel")) {
			endivision = "여행";
		}
		if(division.equals("hobby")) {
			endivision = "취미";
		}
		if(division.equals("computer")) {
			endivision = "컴퓨터";
		}
		if(division.equals("stock")) {
			endivision = "주식";
		}
		if(division.equals("free")) {
			endivision = "자유게시판";
		}
		
		List<BoardVo> boardList = boardService.boardRecen(endivision);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("tit", endivision);
		model.addAttribute("enDivision", division);
		
		
		return "board/posts";
	}
	
	@GetMapping("/create")
	public String createPost(HttpServletRequest request, RedirectAttributes rttr) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			rttr.addFlashAttribute("errm", "로그인 후 글 작성이 가능합니다.");
			return "redirect:/boards";
		}
		if(session != null) {
			LoginVo loginVo = (LoginVo)session.getAttribute("user");
			if(loginVo == null) {
				rttr.addFlashAttribute("errm", "로그인 후 글 작성이 가능합니다.");
				return "redirect:/boards";
			}
		}
		
		return "board/createPost";
	}


	@PostMapping
	public String savePost(@ModelAttribute("board") BoardVo boardVo, HttpServletRequest request, RedirectAttributes rttr) {
		
		HttpSession session = request.getSession(false);
		
		LoginVo loginUser = new LoginVo();
		
		if(session == null) {
			rttr.addFlashAttribute("errm", "로그인 후 글 작성이 가능합니다.");
			return "redirect:/login";
		}
		if(session != null) {
			loginUser = (LoginVo)session.getAttribute("user");
			
			if(loginUser == null) {
				rttr.addFlashAttribute("errm", "로그인 후 글 작성이 가능합니다.");
				return "redirect:/login";
			}
		}
		
		if(boardVo.getTitle().isEmpty() || boardVo.getContents().isEmpty()) {
			rttr.addFlashAttribute("errm", "제목 또는 내용을 입력해주세요.");
			return "redirect:/boards/create";
		}
		
		boardVo.setUserNickName(loginUser.getNickName());
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
		
		rttr.addFlashAttribute("msgm", "글이 작성되었습니다.");
		
		return "redirect:/boards/"+boardVo.getDivision();
	}
	
	@GetMapping("/{division}/{id}")
	public String showPost(@PathVariable("id") String id, HttpServletRequest request, Model model) {
		int boardId = Integer.parseInt(id);
		
		HttpSession session = request.getSession(false);
		
		BoardVo boardVo = boardService.findById(boardId);
		
		if(session == null) {
			boardService.hitsUp(boardId); 
		}
		if(session != null) {
			LoginVo loginUser = (LoginVo)session.getAttribute("user");
			if(loginUser == null) {
				boardService.hitsUp(boardId);
			}
			if(loginUser != null) {
				if(!loginUser.getNickName().equals(boardVo.getUserNickName())) {
					boardService.hitsUp(boardId);
				}
			}
		}
		
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
	public String editPost(@PathVariable("division") String division, @PathVariable("id") String id, Model model,
			HttpServletRequest request, RedirectAttributes rttr) {
		int boardId = Integer.parseInt(id);
		BoardVo boardVo = boardService.findById(boardId);
		
		String endivision = "";
		
		if(boardVo.getDivision().equals("여행")) {
			model.addAttribute("tit", boardVo.getDivision());
			endivision = "travel";
		}
		if(boardVo.getDivision().equals("취미")) {
			model.addAttribute("tit", boardVo.getDivision());
			endivision = "hobby";
		}
		if(boardVo.getDivision().equals("컴퓨터")) {
			model.addAttribute("tit", boardVo.getDivision());
			endivision = "computer";
		}
		if(boardVo.getDivision().equals("주식")) {
			model.addAttribute("tit", boardVo.getDivision());
			endivision = "stock";
		}
		if(boardVo.getDivision().equals("자유게시판")) {
			model.addAttribute("tit", boardVo.getDivision());
			endivision = "free";
		}
	
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			rttr.addFlashAttribute("errm", "수정권한이 없습니다.");

			return "redirect:/boards/"+ endivision + "/" + boardVo.getId();
		} 
		
		LoginVo loginUser = (LoginVo)session.getAttribute("user");
		
		if(loginUser == null) {
			rttr.addFlashAttribute("errm", "로그인정보를 없습니다.");

			return "redirect:/boards/"+ endivision + "/" + boardVo.getId();
		}
		if(loginUser != null) {
			if(!loginUser.getNickName().equals(boardVo.getUserNickName())) {
				rttr.addFlashAttribute("errm", "본인이 쓴 글만 수정이 가능합니다.");

				return "redirect:/boards/"+ endivision + "/" + boardVo.getId();
			}
		}
		
		model.addAttribute("division", division);
		model.addAttribute("board", boardVo);
			
		return "board/editPost";
		
	}
	
	@PatchMapping("/{id}")
	public String updatePost(@ModelAttribute("board") BoardVo boardVo, @PathVariable("id") String id, RedirectAttributes rttr, Model model) {
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
		
		
		if(boardVo.getTitle().isEmpty() || boardVo.getContents().isEmpty()) {
			rttr.addFlashAttribute("division", select);
			rttr.addFlashAttribute("errm", "빈칸으로는 수정할 수 없습니다.");
			return "redirect:/boards/" + select + "/" + boardVo.getId() + "/edit";
		}
		
		int boardId = Integer.parseInt(id);
		boardVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				
		boardService.update(boardId, boardVo);	
		
		rttr.addFlashAttribute("msgm", "수정이 완료되었습니다.");
		
		return "redirect:/boards/"+ select + "/" + boardVo.getId();
	}
	
	@DeleteMapping("/{id}")
	public String deletePost(@ModelAttribute("board") BoardVo boardVo, @PathVariable("id") String id, RedirectAttributes rttr) {
		int boardId = Integer.parseInt(id);
		boardVo.setDeleteTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		boardService.delete(boardId, boardVo);
		rttr.addFlashAttribute("msgm", "삭제가 성공했습니다.");
		return "redirect:/boards";
	}
	
	@GetMapping("/srt")
	public String boardSort2(@RequestParam("srt") String sort, @RequestParam("tit") String tit, 
			@RequestParam(defaultValue = "최신순") String srt, Model model) {
		String division = "";
		if(tit.equals("여행")) {
			division = "travel";
		}
		if(tit.equals("취미")) {
			division = "hobby";
		}
		if(tit.equals("컴퓨터")) {
			division = "computer";
		}
		if(tit.equals("주식")) {
			division = "stock";
		}
		if(tit.equals("자유게시판")) {
			division = "free";
		}
		
		if(sort.equals("인기순")) {
			List<BoardVo> boardList = boardService.boardHits(tit);
			model.addAttribute("boardList", boardList);
			model.addAttribute("tit", tit);
			model.addAttribute("enDivision", division);
			model.addAttribute("srt", srt);
			
			return "board/posts";
		} 
		if(sort.equals("최신순")) {
			List<BoardVo> boardList = boardService.boardRecen(tit);
			model.addAttribute("boardList", boardList);
			model.addAttribute("tit", tit);
			model.addAttribute("enDivision", division);
			model.addAttribute("srt", srt);
			
			return "board/posts";
		}
		return "board/posts";
	}
	
}