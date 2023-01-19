package com.myCommunity.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.thymeleaf.util.MapUtils;

import com.myCommunity.login.LoginServiceImpl;
import com.myCommunity.login.LoginVo;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	LoginServiceImpl loginService;
	
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
	public String userCreate(@ModelAttribute("user") UserVo userVo, RedirectAttributes rttr, Model model, HttpServletRequest request) {	
		Map<String, String> err = new HashMap<>();
		if(userVo.getPassword().isEmpty()) {
			err.put("perr", "비밀번호를 입력해주세요.");
		}
		if(userVo.getEmail().isEmpty()) {
			err.put("eerr", "이메일을 입력해주세요.");
		}
		if(userVo.getGender().equals("x")) {
			err.put("gerr", "성별을 선택해주세요.");
		}
		
		if(!MapUtils.isEmpty(err)) {
			model.addAttribute("err", err);
			return "user/createUser";
		}
				
		userVo.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		userVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		int result = userService.save(userVo);
		
		if(result == 1) {
			LoginVo user = loginService.loginConfirm(userVo.getNickName(), userVo.getPassword());
					
			//세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
			HttpSession session = request.getSession();
			//세션에 로그인 회원 정보 보관
			session.setAttribute("user", user);
			rttr.addFlashAttribute("lmsgm", "회원가입 감사드립니다.");
			return "redirect:/boards";
		} else {
			rttr.addFlashAttribute("lmsgm", "회원가입에 실패했습니다.");
			return "redirect:/create";
		}
	}
	
	
	
}
