package com.myCommunity.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@ResponseBody
	@GetMapping("/checks")
	public int userNickNameCheck(@RequestParam("nickName") String nickName) {
		UserVo user = userService.findBynickName(nickName);
	
		if(user != null || nickName.equals("")) {
			return 0; //가입불가
		}
		return 1; //가입가능
	}
	
	@GetMapping("/create")
	public String userForm() {
		return "user/createUser";
	}
	
	@PostMapping
	public String userCreate(@ModelAttribute("user") UserVo userVo, RedirectAttributes rttr, HttpSession session) {
		userVo.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		userVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		int result = userService.save(userVo);
		
		if(result == 1) {
			UserVo user = userService.findBynickName(userVo.getNickName());
			session.setAttribute("user", user);
			rttr.addFlashAttribute("msg", "회원가입에 성공했습니다.");
			return "redirect:/boards";
		} else {
			rttr.addFlashAttribute("msg", "회원가입에 실패했습니다.");
			return "redirect:/boards";
		}
	}
	
	
	
}
