package com.dyson.swp.member.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dyson.swp.member.dao.BoardDAO;
import com.dyson.swp.member.vo.BoardSerachVO;
import com.dyson.swp.member.vo.BoardVO;
import com.dyson.swp.member.vo.CommVO;
import com.dyson.swp.member.vo.MemberVO;
import com.dyson.swp.member.vo.Paging;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;
	
	@Transactional
	@Override // 게시글 조회 처리
	public Map<String, Object> getBoardList(Paging request) {
		Paging paging = Paging.builder()
										.countPerPage(request.getCountPerPage())
										.currentPage(request.getCurrentPage())
										.totalRecordsCount(boardDAO.getBoardTotalCount())
										.build();
		Map<String, Object> map = new HashMap<>();
		map.put("paging", paging);
		map.put("list", boardDAO.getBoardList(paging));
		
		return map;
	}

	@Override // 게시글 등록
	public void insertBoard(BoardVO bvo) { boardDAO.insertBoard(bvo); }

	@Transactional
	@Override // 상세 게시글 + 댓글 조회 처리 + 조회수
	public Map<String, Object> getBoardOne(int b_seq, MemberVO mvo) {
		Map<String, String> hit = new HashMap<>();
		if (mvo != null) { // 로그인중인지 체크 한다.
			hit.put("mem_email", mvo.getMem_email());
			hit.put("b_seq", Integer.toString(b_seq));
			if (boardDAO.getBoardHit(hit) != 1) { //로그인된 회원정보가 HIT테이블에 존재하는지 확인하고 없으면 조회수 1++
				boardDAO.setBoardHit(hit);
				boardDAO.updateBoardHit(b_seq);
			}
			
		}
		Map<String, Object> map = new HashMap<>();
		map.put("bvo", boardDAO.getBoardOne(b_seq));		// 게시글 select
		map.put("commVO", boardDAO.getCommList(b_seq));		// 게시글에 달린 댓글 select
		map.put("commStep", boardDAO.getCommStep(b_seq));	// 대댓글 check
		
		return map;
	}

	@Override // 게시글 삭제 처리
	public void deleteBoard(int b_seq) { boardDAO.deleteBoard(b_seq); }

	@Override // 게시글 수정 처리
	public void updateBoard(BoardVO bvo) { boardDAO.updateBoard(bvo); }

	@Override // 댓글 추가 처리
	public void insertComm(CommVO commVO) {	boardDAO.insertComm(commVO); }

	@Override // 댓글 삭제 처리
	public void deleteComm(int c_seq) {	boardDAO.deleteComm(c_seq); }
	
	@Transactional
	@Override // 대댓글(답변) 추가 처리
	public void insertReComm(CommVO commVO) {
		commVO.setC_lev(commVO.getC_lev() + 1);
		boardDAO.updateCommLev(commVO); //댓글 삽입전 lev++ 상승
		boardDAO.insertComm(commVO);
	}

	@Transactional
	@Override // 게시글 검색 처리
	public Map<String, Object> getBoardSearchList(Paging request, BoardSerachVO searchVO) {
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("conSelect", searchVO.getConSelect());
		searchMap.put("searchData", searchVO.getSearchData());
		
		Paging paging = Paging.builder()
										.currentPage(request.getCurrentPage())
										.countPerPage(request.getCountPerPage())
										.totalRecordsCount(boardDAO.getboardSearchCount(searchMap))
										.build();

		searchMap.put("startRecord", String.valueOf(paging.getStartRecord()));
		searchMap.put("endRecord", String.valueOf(paging.getEndRecord()));
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", boardDAO.getBoardSearchList(searchMap));
		map.put("paging", paging);
										
		return map;
	}
}
