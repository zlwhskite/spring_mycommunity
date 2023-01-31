package com.myCommunity.login;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import com.myCommunity.attendance.AttendanceService;
import com.myCommunity.attendance.AttendanceVo;
import com.myCommunity.board.BoardServiceImpl;
import com.myCommunity.board.BoardVo;

@Controller
public class LoginController {
	@Autowired
	LoginServiceImpl loginService;
	@Autowired
	BoardServiceImpl boardService;
	@Autowired
	AttendanceService attService;
	
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
		
		if(user.getAuth() == 1) {
			rttr.addFlashAttribute("lmsgm", "관리자 로그인입니다.");
			return "redirect:/boards";
		}
		
		
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DATE, -1);		
		String yesterDate = SDF.format(calendar.getTime());	
		
		String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		AttendanceVo check = attService.findById(user.getId(), nowDate);
		
		
		if(check == null) {
			AttendanceVo att = new AttendanceVo();
			att.setUserId(user.getId());
			att.setAttendanceDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			
			int result = attService.attenSave(att);
			
			if(result == 1) {
				AttendanceVo at= attService.findById(user.getId(), yesterDate);
				
				if(at != null) {
					int cnt = at.getCount() + 1;
					System.out.println("원래 = "+att.getCount());
					System.out.println("+한거 = " + cnt);
					att.setCount(cnt);
					attService.update(user.getId(), att);
					rttr.addFlashAttribute("lmsgm", user.getNickName() + " 님, 안녕하세요! " + "( 연속 " + cnt + "일째 출석 중 )");
				}
				if(at == null) {
					int cnt = 1;
					att.setCount(cnt);
					attService.update(user.getId(), att);
					rttr.addFlashAttribute("lmsgm", user.getNickName() + " 님, 안녕하세요! " + "( 연속 " + cnt + "일째 출석 중 )");
				}
			}
		}
		
		if(check != null) {
			rttr.addFlashAttribute("lmsgm", user.getNickName() + " 님, 안녕하세요! " + "( 연속 " + check.getCount() + "일째 출석 중 )");
		}

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

//어제 날짜 구하기
//Calendar calendar = new GregorianCalendar();
//SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
//calendar.add(Calendar.DATE, -1);		
//String yesterDate = SDF.format(calendar.getTime());	
