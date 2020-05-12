package com.dyson.swp.member.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dyson.swp.interceptor.SessionNames;
import com.dyson.swp.member.service.BoardService;
import com.dyson.swp.member.vo.BoardSerachVO;
import com.dyson.swp.member.vo.BoardVO;
import com.dyson.swp.member.vo.CommVO;
import com.dyson.swp.member.vo.MemberVO;
import com.dyson.swp.member.vo.Paging;

@Controller
@SessionAttributes("bvo")
public class BoardController {

	@Inject
	private BoardService boardService;
	
	/* 게시판 리스트 조회 */
	@GetMapping("/board")
	public String getBoardList(Paging request, Model model) {
		Map<String, Object> map = boardService.getBoardList(request);
		model.addAttribute("paging", map.get("paging"));
		model.addAttribute("list", map.get("list"));
		return "board/boardView";
	}
	
	/* 게시판 검색어 조회*/
	@GetMapping("/boardSearch")
	public String getBoardSearchList(BoardSerachVO searchVO, Paging request, Model model) {
		System.out.println("\n searchVO data : " + searchVO + "\n");
		System.out.println("\n request data : " + request + "\n");
		Map<String, Object> map = boardService.getBoardSearchList(request, searchVO);
		System.out.println("\n paging data : " + map.get("paging") + "\n");
		model.addAttribute("paging", map.get("paging"));
		model.addAttribute("list", map.get("list"));
		model.addAttribute("searchVO", searchVO);
		return "board/boardSearchView";
	}
	
	/* 게시글 작성 페이지 이동 */
	@GetMapping("/board/form")
	public String boardForm() { return "board/boardWriteView"; }
	
	/* 게시글 등록  */
	@PostMapping("board/insert")
	@ResponseBody
	public void insertBoard(BoardVO bvo, HttpServletRequest request) {
		bvo.setB_cre_ip(request.getRemoteAddr()); // 글 작성시 회원 IP 정보 값
		boardService.insertBoard(bvo);
	}
	
	/* 게시글 삭제 */
	@DeleteMapping("/board/delete/{b_seq}")
	@ResponseBody
	public void deleteBoard(@PathVariable int b_seq) { boardService.deleteBoard(b_seq); }
	
	/* 게시글 상세 조회 + 댓글 조회 */
	@GetMapping("/board/content/{b_seq}")
	public String getBoardList(@PathVariable int b_seq, Model model, HttpSession httpSession) {
		MemberVO mvo = (MemberVO) httpSession.getAttribute(SessionNames.LOGIN);
		
		Map<String, Object> map = boardService.getBoardOne(b_seq, mvo);
		model.addAttribute("bvo", map.get("bvo"));
		model.addAttribute("commVO", map.get("commVO"));
		model.addAttribute("commStep", map.get("commStep"));
		System.out.println("\n commVO Data : " + map.get("commVO") + "\n");
		return "board/boardContentView";
	}
	
	/* 게시글 댓글 등록 */     
	@PostMapping("/board/insertComm")
	@ResponseBody
	public void insertComm(CommVO commVO) { boardService.insertComm(commVO); }
	
	/* 게시글 대댓글(답글) 등록 */     
	@PostMapping("/board/insertReComm")
	@ResponseBody
	public void insertReComm(CommVO commVO) { boardService.insertReComm(commVO); }
	
	/* 게시글 댓글 삭제 */
	@DeleteMapping("/board/commDelete/{c_seq}")
	@ResponseBody
	public void deleteComm(@PathVariable int c_seq) { boardService.deleteComm(c_seq); }
	
	/* 게시글 수정 페이지 이동 */
	@GetMapping("/board/modify")
	public String boardModiForm(@ModelAttribute("bvo") BoardVO bvo) { return "board/boardModifyView"; }
	
	/* 게시글 수정 */
	@PostMapping("/board/update")
	@ResponseBody
	public void updateBoard(BoardVO bvo) { boardService.updateBoard(bvo); }
}
