package com.myCommunity.comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import com.myCommunity.board.BoardVo;

@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentServiceImpl commentService;
	
	@PostMapping("/create")
	public String commentCreate(@ModelAttribute("comment") CommentVo commentVo, @RequestParam("division") String division, 
			@RequestParam("id") String boardId, @RequestParam("commentNickName") String userNickName, RedirectAttributes rttr) {

	
		int boarId = Integer.parseInt(boardId);
		commentVo.setBoardId(boarId);
		commentVo.setUserNickName(userNickName);
		commentVo.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		commentVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		if(division.equals("여행")) {
			division = "travel";
		}
		if(division.equals("취미")) {
			division = "hobby";
		}
		if(division.equals("컴퓨터")) {
			division = "computer";
		}
		if(division.equals("주식")) {
			division = "stock";
		}
		if(division.equals("자유게시판")) {
			division = "free";
		}
		
		if(commentVo.getContents().isEmpty()) {
			rttr.addFlashAttribute("errm", "공백으로는 댓글을 등록할 수 없습니다.");
			return "redirect:/boards/"+ division + "/" + boarId;
		}
		
		commentService.commentCreate(commentVo);
		
		rttr.addFlashAttribute("msgm", "댓글이 등록되었습니다.");
		
		return "redirect:/boards/"+ division + "/" + boarId;
	}
	
	@GetMapping("/{id}/edit")
	public String commentEdit(@PathVariable("id") String id, Model model) {
		
		return "";
	}
	
	@RequestMapping(value="/{id}", params="action=modify")
	public String commentUpdate(@ModelAttribute("comm") CommentVo commentVo, @PathVariable("id") String id, @RequestParam("division") String division,
			RedirectAttributes rttr) {
		int commentId = Integer.parseInt(id);
		
		CommentVo com = commentService.findById(commentId);
		
		commentVo.setModifyTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		com.setModifyTime(commentVo.getModifyTime());
		com.setContents(commentVo.getContents());
		
		if(division.equals("여행")) {
			division = "travel";
		}
		if(division.equals("취미")) {
			division = "hobby";
		}
		if(division.equals("컴퓨터")) {
			division = "computer";
		}
		if(division.equals("주식")) {
			division = "stock";
		}
		if(division.equals("자유게시판")) {
			division = "free";
		}
		
		if(commentVo.getContents().isEmpty()) {
			rttr.addFlashAttribute("errm", "공백으로는 댓글을 수정할 수 없습니다.");
			return "redirect:/boards/" + division + "/" + com.getBoardId();
		}
		
		
		commentService.commentModify(commentId, com);
		
		rttr.addFlashAttribute("msgm", "댓글이 수정되었습니다.");
		
		return "redirect:/boards/" + division + "/" + com.getBoardId();
	}
	
	@RequestMapping(value="/{id}", params="action=delete")
	public String commentDelete(@ModelAttribute("comm") CommentVo commentVo, @PathVariable("id") String id, @RequestParam("division") String division,
			RedirectAttributes rttr) {
		int commentId = Integer.parseInt(id);

		commentVo = commentService.findById(commentId);
		commentVo.setDeleteTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		
		if(division.equals("여행")) {
			division = "travel";
		}
		if(division.equals("취미")) {
			division = "hobby";
		}
		if(division.equals("컴퓨터")) {
			division = "computer";
		}
		if(division.equals("주식")) {
			division = "stock";
		}
		if(division.equals("자유게시판")) {
			division = "free";
		}
		
		if(commentVo.getContents().isEmpty()) {
			rttr.addFlashAttribute("errm", "공백으로는 댓글을 수정할 수 없습니다.");
			return "redirect:/boards/" + division + "/" + commentVo.getBoardId();
		}
		
		commentService.commentDelete(commentId, commentVo);
		
		rttr.addFlashAttribute("msgm", "댓글이 삭제되었습니다.");
		
		return "redirect:/boards/" + division + "/" + commentVo.getBoardId();
	}
}
