package com.myCommunity.admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myCommunity.board.BoardServiceImpl;
import com.myCommunity.board.BoardVo;
import com.myCommunity.login.LoginVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	BoardServiceImpl boardService;
	
	
	@GetMapping()
	public String index(HttpServletRequest request, RedirectAttributes rttr, Model model) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			rttr.addFlashAttribute("errm", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		if(session != null) {
			LoginVo loginVo = (LoginVo)session.getAttribute("user");
			if(loginVo == null) {
				rttr.addFlashAttribute("errm", "로그인정보를 획득하지 못했습니다.");
				return "redirect:/login";
			}
			if(loginVo.getAuth() == 0) {
				rttr.addFlashAttribute("errm", "관리자권한이 없습니다.");
				return "redirect:/login";
			}
		}

		String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<AdminVo> todayUser = adminService.todayUserList(nowDate);
		List<AdminVo> todayBoard = adminService.todayBoardList(nowDate);
		List<AdminVo> userList = adminService.userList();
		List<AdminVo> boardList = adminService.boardList();
		
		if(todayUser.isEmpty()) {
			model.addAttribute("todayUser", 0);
		}
		if(todayBoard.isEmpty()) {
			model.addAttribute("todayBoard", 0);
		}
		
		List<BoardVo> travelList = boardService.findAllTravel();
		List<BoardVo> hobbyList = boardService.findAllHobby();
		List<BoardVo> computerList = boardService.findAllComputer();
		List<BoardVo> stockList = boardService.findAllStock();
		List<BoardVo> workoutList = boardService.findAllWorkout();
		List<BoardVo> freeList = boardService.findAllFree();
		
		model.addAttribute("todayUserList", todayUser);
		model.addAttribute("todayBoardList", todayBoard);
		model.addAttribute("userList", userList);
		model.addAttribute("boardList", boardList);
		
		model.addAttribute("travelList", travelList);
		model.addAttribute("hobbyList", hobbyList);
		model.addAttribute("computerList", computerList);
		model.addAttribute("stockList", stockList);
		model.addAttribute("workoutList", workoutList);
		model.addAttribute("freeList", freeList);

		return "admin/index";
	}
	
	
	
	
	
	/*
	@GetMapping("/edit")
	public String edit(HttpServletRequest request, RedirectAttributes rttr, Model model) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			rttr.addFlashAttribute("errm", "로그인이 필요합니다.");
			return "redirect:/login";
		}
		if(session != null) {
			LoginVo loginVo = (LoginVo)session.getAttribute("user");
			if(loginVo == null) {
				rttr.addFlashAttribute("errm", "로그인정보를 획득하지 못했습니다.");
				return "redirect:/login";
			}
			if(loginVo.getAuth() == 0) {
				rttr.addFlashAttribute("errm", "관리자권한이 없습니다.");
				return "redirect:/login";
			}
		}
		
		List<CategoryVo> categoryList = categoryService.CategoryList();
		
		model.addAttribute("categoryList", categoryList);
		return "admin/adminEdit";
	}
	
	@PostMapping("/categoryname/edit")
	public String nameEdit(@RequestParam("categoryName1") String name1, @RequestParam("categoryName2") String name2, RedirectAttributes rttr, Model model) {
		int i = categoryService.categoryEdit(name1, name2);
		
		if(i == 1) {
			List<CategoryVo> categoryList = categoryService.CategoryList();
			
			model.addAttribute("categoryList", categoryList);
			rttr.addFlashAttribute("msgm", "수정이 완료되었습니다.");
			
			return "redirect:/admin/edit";
		}else {
			rttr.addFlashAttribute("errm", "수정에 실패하였습니다.");
			return "redirect:/admin/edit";
		}
	
	}
	
	@PostMapping("/order/edit")
	public String orderEdit(@RequestParam("categoryId") int id1, @RequestParam("changeId") int id2, RedirectAttributes rttr, Model model) {
		CategoryVo ct1 = categoryService.findById(id1);
		CategoryVo ct2 = categoryService.findById(id2);
		
		int result1 = categoryService.categoryOrderEdit(ct2.getId(), ct1.getCategoryName());
		int result2 = categoryService.categoryOrderEdit(ct1.getId(), ct2.getCategoryName());
		
		if(result1 == 1 && result2 == 1) {
			List<CategoryVo> categoryList = categoryService.CategoryList();
			
			model.addAttribute("categoryList", categoryList);
			rttr.addFlashAttribute("msgm", "수정이 완료되었습니다.");
			
			return "redirect:/admin/edit";
		}else {
			rttr.addFlashAttribute("errm", "수정에 실패하였습니다.");
			return "redirect:/admin/edit";
		}
		
		
	}
	*/
}
