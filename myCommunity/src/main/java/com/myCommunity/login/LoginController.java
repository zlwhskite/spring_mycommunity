package com.myCommunity.login;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.myCommunity.board.BoardVo;

@Controller
public class LoginController {
	@Autowired
	LoginServiceImpl loginService;
	
	@PostMapping("/login")
	public String login(@ModelAttribute("login") LoginVo loginVo) {
		LoginVo user = loginService.loginConfirm(loginVo.getNickName(), loginVo.getPassword());
		System.out.println("a");
		if(user == null) {
			return "board/index"; //가입불가
		}
		
		return "redirect:/boards";
	}
	
}
