package com.myCommunity.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthToggleButtonUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myCommunity.board.BoardServiceImpl;
import com.myCommunity.board.BoardVo;
import com.myCommunity.login.LoginVo;

@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentServiceImpl commentService;
	@Autowired
	BoardServiceImpl boardService;
	
	@PostMapping("/create")
	public String commentCreate(@ModelAttribute("comment") CommentVo commentVo, @RequestParam("division") String division, 
			@RequestParam("id") int boardId, HttpServletRequest request, RedirectAttributes rttr) {		
		division = boardService.dvchKE(division);
		
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			rttr.addFlashAttribute("errm", "로그인 후 댓글작성이 가능합니다.");

			return "redirect:/boards/"+ division + "/" + boardId;
		}
		
		LoginVo loginUser = (LoginVo)session.getAttribute("user");
		
		if(loginUser == null) {
			rttr.addFlashAttribute("errm", "로그인 후 댓글작성이 가능합니다.");
			return "redirect:/boards/"+ division + "/" + boardId;
		}
		if(commentVo.getContents().isEmpty()) {
			rttr.addFlashAttribute("errm", "공백으로는 댓글을 등록할 수 없습니다.");
			return "redirect:/boards/"+ division + "/" + boardId;
		}
		
		commentVo.setBoardId(boardId);
		commentVo.setUserNickName(loginUser.getNickName());
		commentVo.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		commentVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		
		commentService.commentCreate(commentVo);
		
		rttr.addFlashAttribute("msgm", "댓글이 등록되었습니다.");
		
		return "redirect:/boards/"+ division + "/" + boardId;
	}
	

	@RequestMapping(value="/{id}", params="action=modify")
	public String commentUpdate(@ModelAttribute("comm") CommentVo commentVo, @PathVariable("id") int commentId, @RequestParam("division") String division,
			RedirectAttributes rttr) {	
		CommentVo com = commentService.findById(commentId);
		
		commentVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		com.setModifyTime(commentVo.getModifyTime());
		com.setContents(commentVo.getContents());
		
		division = boardService.dvchKE(division);
		
		if(commentVo.getContents().isEmpty()) {
			rttr.addFlashAttribute("errm", "공백으로는 댓글을 수정할 수 없습니다.");
			return "redirect:/boards/" + division + "/" + com.getBoardId();
		}
		
		
		commentService.commentModify(commentId, com);
		
		rttr.addFlashAttribute("msgm", "댓글이 수정되었습니다.");
		
		return "redirect:/boards/" + division + "/" + com.getBoardId();
	}
	
	@RequestMapping(value="/{id}", params="action=delete")
	public String commentDelete(@ModelAttribute("comm") CommentVo commentVo, @PathVariable("id") int commentId, @RequestParam("division") String division,
			RedirectAttributes rttr) {
		commentVo = commentService.findById(commentId);
		commentVo.setDeleteTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		division = boardService.dvchKE(division);
		
		commentService.commentDelete(commentId, commentVo);
		
		rttr.addFlashAttribute("msgm", "댓글이 삭제되었습니다.");
		
		return "redirect:/boards/" + division + "/" + commentVo.getBoardId();
	}
}
