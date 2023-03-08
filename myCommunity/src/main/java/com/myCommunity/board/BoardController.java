package com.myCommunity.board;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.myCommunity.comment.CommentServiceImpl;
import com.myCommunity.comment.CommentVo;
import com.myCommunity.criteria.Criteria;
import com.myCommunity.criteria.CriteriaMapper;
import com.myCommunity.criteria.CriteriaService;
import com.myCommunity.criteria.Criteria;
import com.myCommunity.criteria.Pagination;
import com.myCommunity.login.LoginVo;
import com.myCommunity.user.UserVo;


@Controller
@RequestMapping("/boards")
public class BoardController {
	@Autowired
	private BoardServiceImpl boardService;
	@Autowired
	CommentServiceImpl commentService;
	@Autowired
	CriteriaService criteriaService;
	
	@Value("${file.dir}")
	private String fileDir;

	
	@RequestMapping
	public String index(HttpServletRequest request, Model model) {
		List<BoardVo> boardList = boardService.findAll();
		List<BoardVo> travelList = boardService.findAllTravel();
		List<BoardVo> hobbyList = boardService.findAllHobby();
		List<BoardVo> computerList = boardService.findAllComputer();
		List<BoardVo> stockList = boardService.findAllStock();
		List<BoardVo> workoutList = boardService.findAllWorkout();
		List<BoardVo> freeList = boardService.findAllFree();
		
		HttpSession session = request.getSession(false);
		
		if (session == null) {

			model.addAttribute("boardList", boardList);
			model.addAttribute("travelList", travelList);
			model.addAttribute("hobbyList", hobbyList);
			model.addAttribute("computerList", computerList);
			model.addAttribute("stockList", stockList);
			model.addAttribute("workoutList", workoutList);
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
			model.addAttribute("workoutList", workoutList);
			model.addAttribute("freeList", freeList);
			
			return "board/index";
		}

		model.addAttribute("boardList", boardList);
		model.addAttribute("travelList", travelList);
		model.addAttribute("hobbyList", hobbyList);
		model.addAttribute("computerList", computerList);
		model.addAttribute("stockList", stockList);
		model.addAttribute("workoutList", workoutList);
		model.addAttribute("freeList", freeList);
		
		model.addAttribute("user", loginUser);
	
		return "board/index";
	}
	
	/*
	 * @RequestParam은 다양하게 타입을 설정해서 받을 수 있다
	 * 하지만 지정한 키값이 존재하지않으면 400에러발생 -> DefaultValue로 예방
	 * 
	 * 결론 -> 이렇게 쓰는 것은 좋지않다
	 */
	@GetMapping("/{division}")
	public String divisionBoard(@PathVariable("division") String division, @RequestParam(value="page", required=false, defaultValue = "1") int page, Model model) {
		if(division.equals("hot")) {
			List<BoardVo> hotCnt = boardService.findAllHot();
			int totalCount = hotCnt.size();

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
			
			List<BoardVo> boardList = criteriaService.findAllHit(start, size);
			model.addAttribute("boardList", boardList);
			model.addAttribute("pagination", pn);
			model.addAttribute("tit", "인기 글");
			model.addAttribute("enDivision", "hot");
			
			return "board/posts";
		}
	
		String endivision = boardService.dvchEK(division);
		
		int totalCount = criteriaService.count(endivision);

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
		
		List<BoardVo> boardList = criteriaService.findAll(start, size, endivision);

		model.addAttribute("boardList", boardList);
		model.addAttribute("pagination", pn);
		model.addAttribute("tit", endivision);
		model.addAttribute("enDivision", division);
			
		return "board/posts";
	}
	

	@GetMapping("/create")
	public String createPost(HttpServletRequest request, RedirectAttributes rttr) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			rttr.addFlashAttribute("errm", "로그인 후 글 작성이 가능합니다.");
			return "redirect:/login";
		}
		if(session != null) {
			LoginVo loginVo = (LoginVo)session.getAttribute("user");
			if(loginVo == null) {
				rttr.addFlashAttribute("errm", "로그인 후 글 작성이 가능합니다.");
				return "redirect:/login";
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
		
		boardService.save(boardVo);
		
		boardVo.setDivision(boardService.dvchKE(boardVo.getDivision()));
		
		rttr.addFlashAttribute("msgm", "글이 작성되었습니다.");
		
		return "redirect:/boards/"+boardVo.getDivision();
	}
	
	@GetMapping("/{division}/{id}")
	public String showPost(@PathVariable("id") int boardId, @RequestParam(value="page", required=false, defaultValue = "1") int page, 
			HttpServletRequest request, Model model) {
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
		List<CommentVo> replyList = commentService.replyList(boardId);
		List<CommentVo> commentSize = commentService.commentListdelete(boardId);
		
		model.addAttribute("tit", boardVo.getDivision());
		model.addAttribute("enDivision", boardService.dvchKE(boardVo.getDivision()));
		
		int totalCount = criteriaService.count(boardVo.getDivision());

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
		
		List<BoardVo> boardList = criteriaService.findAll(start, size, boardVo.getDivision());
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagination", pn);
		model.addAttribute("board", boardVo);
		model.addAttribute("commentList", commentList);
		model.addAttribute("replyList", replyList);
		model.addAttribute("commentSize", commentSize);
		
		return "board/showPost";
	}
	
	@GetMapping("/{division}/{id}/edit")
	public String editPost(@PathVariable("id") int boardId, Model model, HttpServletRequest request, RedirectAttributes rttr) {
		BoardVo boardVo = boardService.findById(boardId);
		
		model.addAttribute("tit", boardVo.getDivision());
		String endivision = boardService.dvchKE(boardVo.getDivision());
		
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
				if(loginUser.getAuth() == 1) {
					model.addAttribute("division", endivision);
					model.addAttribute("board", boardVo);
						
					return "board/editPost";
				}
				rttr.addFlashAttribute("errm", "본인이 쓴 글만 수정이 가능합니다.");

				return "redirect:/boards/"+ endivision + "/" + boardVo.getId();
			}
		}
		
		model.addAttribute("division", endivision);
		model.addAttribute("board", boardVo);
			
		return "board/editPost";
	}
	
	@PatchMapping("/{id}")
	public String updatePost(@ModelAttribute("board") BoardVo boardVo, @PathVariable("id") int boardId, RedirectAttributes rttr, Model model) {		
		boardVo.setDivision(boardService.dvchKE(boardVo.getDivision()));
		String select = boardService.dvchKE(boardVo.getDivision());
		
		if(boardVo.getTitle().isEmpty() || boardVo.getContents().isEmpty()) {
			rttr.addFlashAttribute("division", select);
			rttr.addFlashAttribute("errm", "빈칸으로는 수정할 수 없습니다.");
			return "redirect:/boards/" + select + "/" + boardVo.getId() + "/edit";
		}
		
		boardVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				
		boardService.update(boardId, boardVo);	
		
		rttr.addFlashAttribute("msgm", "수정이 완료되었습니다.");
		
		return "redirect:/boards/"+ select + "/" + boardVo.getId();
	}
	
	@DeleteMapping("/{id}")
	public String deletePost(@ModelAttribute("board") BoardVo boardVo, @PathVariable("id") int boardId, RedirectAttributes rttr) {
		boardVo.setDeleteTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		String division = boardService.dvchKE(boardVo.getDivision());
		
		boardService.delete(boardId, boardVo);
		rttr.addFlashAttribute("msgm", "삭제가 성공했습니다.");
		return "redirect:/boards/" + division;
	}
	
	@GetMapping("/srt")
	public String boardSort2(@RequestParam("srt") String sort, @RequestParam("tit") String tit, 
			@RequestParam(defaultValue = "최신순") String srt, @RequestParam(value="page", required=false, defaultValue = "1") int page, Model model) {
		String division = boardService.dvchKE(tit);
		
		if(sort.equals("인기순")) {
			int totalCount = criteriaService.count(tit);
			
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
			
			List<BoardVo> boardList = criteriaService.findHit(start, size, tit);
			
			model.addAttribute("boardList", boardList);
			model.addAttribute("pagination", pn);
			model.addAttribute("tit", tit);
			model.addAttribute("enDivision", division);
			model.addAttribute("srt", "인기순");
			
			return "board/posts";
		} 
		if(sort.equals("최신순")) {
			int totalCount = criteriaService.count(tit);
			
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
			
			List<BoardVo> boardList = criteriaService.findAll(start, size, tit);
			
			model.addAttribute("boardList", boardList);
			model.addAttribute("pagination", pn);
			model.addAttribute("tit", tit);
			model.addAttribute("enDivision", division);
			model.addAttribute("srt", "최신순");
			
			return "board/posts";
		}
		return "board/posts";
	}
	
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile)  {
		JsonObject jsonObject = new JsonObject();
		//외부 경로
		String fileRoot = fileDir;
		//오리지날 파일명
		String originalFileName = multipartFile.getOriginalFilename();
		//파일 확장자
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		//저장될 파일 명
		String savedFileName = UUID.randomUUID() + extension;
		
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			//파일 저장
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			jsonObject.addProperty("url", "/file/"+savedFileName); 
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			//파일 삭제
			FileUtils.deleteQuietly(targetFile);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		String result = jsonObject.toString();
		return result;
	}
		
}