package com.dyson.swp.member.service;

import java.util.Map;

import com.dyson.swp.member.vo.BoardSerachVO;
import com.dyson.swp.member.vo.BoardVO;
import com.dyson.swp.member.vo.CommVO;
import com.dyson.swp.member.vo.MemberVO;
import com.dyson.swp.member.vo.Paging;

public interface BoardService {

	Map<String, Object> getBoardList(Paging paging);

	void insertBoard(BoardVO bvo);

	Map<String, Object> getBoardOne(int b_seq, MemberVO mvo);

	void deleteBoard(int b_seq);

	void updateBoard(BoardVO bvo);

	void insertComm(CommVO commVO);

	void deleteComm(int c_seq);

	void insertReComm(CommVO commVO);

	Map<String, Object> getBoardSearchList(Paging request, BoardSerachVO searchVO);
}
