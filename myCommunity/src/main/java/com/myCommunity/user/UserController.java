package com.myCommunity.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.MapUtils;

import com.myCommunity.attendance.AttendanceService;
import com.myCommunity.attendance.AttendanceVo;
import com.myCommunity.board.BoardServiceImpl;
import com.myCommunity.board.BoardVo;
import com.myCommunity.comment.CommentServiceImpl;
import com.myCommunity.comment.CommentVo;
import com.myCommunity.login.LoginServiceImpl;
import com.myCommunity.login.LoginVo;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	LoginServiceImpl loginService;
	@Autowired
	BoardServiceImpl boardService;
	@Autowired
	CommentServiceImpl commentService;
	@Autowired
	AttendanceService attService;
	
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
			
			//유저출석
			AttendanceVo att =  new AttendanceVo();
			
			att.setUserId(user.getId());
			att.setAttendanceDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			attService.attenSave(att);
			
			rttr.addFlashAttribute("lmsgm", "회원가입 감사드립니다.");
			return "redirect:/boards";
		} else {
			rttr.addFlashAttribute("lmsgm", "회원가입에 실패했습니다.");
			return "redirect:/create";
		}
	}
	
	@GetMapping("/edit")
	public String userEdit(RedirectAttributes rttr, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		
		LoginVo loginVo = (LoginVo)session.getAttribute("user");
		
		if(loginVo == null) {
			rttr.addFlashAttribute("lmsgm", "정보를 읽어오는 중 오류가 발생했습니다.");
			return "redirect:/boards";
		}else {
			UserVo user = userService.findBynickName(loginVo.getNickName());
			
			if(user.getAuth() == 1) {
				List<UserVo> userList = userService.findAll();
				model.addAttribute("userList", userList);
				model.addAttribute("userIn", user);
				return "user/userInfo";
			}
			
			model.addAttribute("userIn", user);
			
			return "user/userInfo";
		}
	}
	
	
	@RequestMapping(value="/{id}", params="action=modify")
	public String userModify(@ModelAttribute("user") UserVo userVo, @PathVariable("id") int userId, RedirectAttributes rttr) {
		userVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		userService.update(userId, userVo);
		
		rttr.addFlashAttribute("lmsgm", userVo.getNickName() + " 님," + " 수정이 완료되었습니다.");
		return "redirect:/boards";
	}
	
	@RequestMapping(value="/{id}", params="action=pwdModify")
	public String pwdModify() {
		return "user/userPassEdit";
	}
	
	@PostMapping("/modi")
	public String pwdUpdate(@RequestParam("pswd1") String newpwd, @RequestParam("pswd2") String confirmpwd, @RequestParam("password") String oldpwd, 
			RedirectAttributes rttr, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		LoginVo loginVo = (LoginVo)session.getAttribute("user");
		
		if(loginVo == null) {
			rttr.addFlashAttribute("lmsgm", "수정하는 중 오류가 발생했습니다.");
			return "redirect:/boards";
		}else {
			if(!newpwd.equals(confirmpwd)) {
				UserVo user = userService.findById(loginVo.getId());
				model.addAttribute("userIn", user);
				rttr.addFlashAttribute("lmsgm", "비밀번호가 일치하지않습니다");
				return "redirect:/users/edit";
			}
			UserVo user = userService.findById(loginVo.getId());
			user.setPassword(newpwd);
			user.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			
			int result = userService.pwdModify(loginVo.getId(), user, oldpwd);   
			
			if(result == 0) {
				rttr.addFlashAttribute("lmsgm", "기존 비밀번호가 일치하지않습니다");
				return "redirect:/users/edit";
			}
			
			session.invalidate();
			rttr.addFlashAttribute("lmsgm", "비밀번호가 변경되었습니다." + " 다시 로그인을 해주세요.");
			return "redirect:/boards";
		}
	}
	
	@RequestMapping(value="/{id}", params="action=delete")
	public String userDelete(RedirectAttributes rttr, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		LoginVo loginVo = (LoginVo)session.getAttribute("user");
		
		if(loginVo == null) {
			rttr.addFlashAttribute("lmsgm", "수정하는 중 오류가 발생했습니다.");
			return "redirect:/boards";
		}else {
			UserVo user = userService.findById(loginVo.getId());
			user.setDeleteTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			
			BoardVo bv = new BoardVo();
			bv.setDeleteTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			
			CommentVo cv = new CommentVo();
			cv.setUserNickName(loginVo.getNickName());
			cv.setDeleteTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			

			userService.delete(loginVo.getId(), user);
			boardService.userDelete(user.getNickName(), bv);
			commentService.commentUserDelete(cv);
			attService.delete(loginVo.getId());
			
			session.invalidate();
			rttr.addFlashAttribute("lmsgm", "회원탈퇴를 성공했습니다." + " 감사합니다.");
			return "redirect:/boards";
			
		}
	}
	
	
	@GetMapping("/findUser")
	public String findUserForm() {
		return "user/findUser";
	}	
	@PostMapping("/findUser")
	public String findUserNickName(@RequestParam("email") String email, Model model, RedirectAttributes rttr) {
		UserVo user = userService.findUserNickName(email);
		
		if(user == null) {
			rttr.addFlashAttribute("lmsgm", "회원정보가 존재하지않습니다.");
			return "redirect:/users/findUser";
		}
		
		model.addAttribute("userNickName", user.getNickName());
		return "user/findResultUser";
	}
	
	@GetMapping("/findPwd")
	public String findPwdForm() {
		return "user/findpwd";
	}
	
	@PostMapping("/findPwd")
	public String findUserPwd(@RequestParam("nickName") String nickName, @RequestParam("email") String email, 
			Model model, RedirectAttributes rttr) {
		UserVo user = userService.findPwd(nickName, email);
		
		if(user == null) {
			rttr.addFlashAttribute("lmsgm", "회원정보가 존재하지않습니다.");
			return "redirect:/users/findPwd";
		}
		
		model.addAttribute("user", user);
		return "user/findResultPwd";
	}
	@PostMapping("/reset")
	public String resetPwd(@RequestParam("pswd1") String pwd1, @RequestParam("pswd2") String pwd2, @RequestParam("id") int userId, 
			RedirectAttributes rttr, Model model) {
		if(!pwd1.equals(pwd2)) {
			rttr.addFlashAttribute("lmsgm", "입력하신 비밀번호가 일치하지않습니다.");
			return "redirect:/users/findPwd";
		}
		
		userService.resetPwd(userId, pwd1);
		rttr.addFlashAttribute("msgm", "비밀번호가 재설정되었습니다.");
		return "redirect:/boards";
	}

	
}
