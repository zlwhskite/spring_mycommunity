package com.myCommunity.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myCommunity.board.BoardServiceImpl;
import com.myCommunity.board.BoardVo;

@Controller
public class LoginController {
	@Autowired
	LoginServiceImpl loginService;
	@Autowired
	BoardServiceImpl boardService;
	
	@GetMapping("/login")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("login") LoginVo loginVo, HttpServletRequest request, RedirectAttributes rttr, Model model) {
		
		LoginVo user = loginService.loginConfirm(loginVo.getNickName(), loginVo.getPassword());
	
		if(user == null) {
			model.addAttribute("msg", "아이디 또는 비밀번호가 일치하지않습니다.");
			return "user/loginForm"; //가입불가
		}
		
		//세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
		HttpSession session = request.getSession();
		//세션에 로그인 회원 정보 보관
		session.setAttribute("user", user);
		
		rttr.addFlashAttribute("lmsgm", user.getNickName() + " 님, 안녕하세요!");
		
		return "redirect:/boards";
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes rttr) {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		
		rttr.addFlashAttribute("msgm", "로그아웃이 되었습니다.");
		
		return "redirect:/boards";
	}
	
}
