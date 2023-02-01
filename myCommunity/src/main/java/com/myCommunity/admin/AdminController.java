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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myCommunity.board.BoardVo;
import com.myCommunity.login.LoginVo;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	
	
	@GetMapping("/admin")
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
		
		model.addAttribute("todayUserList", todayUser);
		model.addAttribute("todayBoardList", todayBoard);
		model.addAttribute("userList", userList);
		model.addAttribute("boardList", boardList);
		
		
		
		return "admin/index";
	}
}
