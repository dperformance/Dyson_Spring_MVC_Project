package com.dyson.swp.member.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dyson.swp.member.vo.BoardSerachVO;
import com.dyson.swp.member.vo.BoardVO;
import com.dyson.swp.member.vo.CommVO;
import com.dyson.swp.member.vo.MemberVO;
import com.dyson.swp.member.vo.Paging;

@Repository
public class BoardDAO {

	@Inject
	private SqlSessionTemplate mybatis;

	public List<BoardVO> getBoardList(Paging paging) {
		return mybatis.selectList("BoardMapper.getBoardList", paging);
	}

	public void insertBoard(BoardVO bvo) {
		mybatis.update("BoardMapper.insertBoard", bvo);
	}

	public BoardVO getBoardOne(int b_seq) {
		return mybatis.selectOne("BoardMapper.getBoardOne", b_seq);
	}
	
	public List<CommVO> getCommList(int b_seq) {
		return mybatis.selectList("BoardMapper.getCommList", b_seq);
	}

	public Object deleteBoard(int b_seq) {
		return mybatis.update("BoardMapper.deleteBoard", b_seq);
	}

	public void updateBoard(BoardVO bvo) {
		mybatis.update("BoardMapper.updateBoard", bvo);
	}

	public int getBoardTotalCount() {
		return mybatis.selectOne("BoardMapper.getBoardTotalCount");
	}

	public void insertComm(CommVO commVO) {
		mybatis.update("BoardMapper.insertComm", commVO);
	}

	public String getCommStep(int b_seq) {
		return mybatis.selectOne("BoardMapper.getCommStep", b_seq);
	}

	public void deleteComm(int c_seq) {
		mybatis.update("BoardMapper.deleteComm", c_seq);
	}

	public void updateCommLev(CommVO commVO) {
		mybatis.update("BoardMapper.updateCommLev", commVO);
	}

	public List<BoardVO> getBoardSearchList(Map<String, String> searchMap) {
		return mybatis.selectList("BoardMapper.getBoardSearchList", searchMap);
	}

	public int getboardSearchCount(Map<String, String> searchMap) {
		return mybatis.selectOne("BoardMapper.getBoardSearchCount", searchMap);
	}

	public int getBoardHit(Map<String, String> map) {
		return mybatis.selectOne("BoardMapper.getBoardHit", map); 
	}

	public void setBoardHit(Map<String, String> map) {
		mybatis.update("BoardMapper.setBoardHit", map);
	}

	public void updateBoardHit(int b_seq) {
		mybatis.update("BoardMapper.updateBoardHit", b_seq);
	}
}
