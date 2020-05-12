package com.dyson.swp.member.vo;

import lombok.Builder;
import lombok.Data;

/*
	오프셋 기반 페이지네이션 (Offset-based Pagination)
	
	커서 기반 페이지네이션 (Cursor-based Pagination)
		- 클라이언트가 가져간 마지막 row의 순서상 다음 row들을 n개 요청/응답하게 구현
 */
@Data
public class Paging {
	
	private int countPerPage = 15;		//게시판에 표시할 게시글 수 (초기값 15개)
	private int pagePerBlock = 5;		//블록당 표시하는 페이지 개수 [1][2][3][4][5] / [6][7][8][9][10]
	
	private int currentPage = 1;		//현재 페이지
	private int currentBlock = 1;		//현재 블록
	
	private int totalRecordsCount = 0;	//DB에 등록된 총 게시글 수
	private int totalPageCount = 0;		//전체 페이지 개수
	
	private int startBlock = 0;			//현재 블록의 시작 페이지 번호 
	private int endBlock = 0;			//현재 블록의 끝 페이지 번호    
	
	private int startRecord = 0;		//현재 페이지에 표시할 첫 번째 글 번호 (select문에 사용함) 
	private int endRecord = 0;			//현재 페이지에 표시할 마지막 글 번호  (select문에 사용함)

	public Paging() { } //기본생성자 선언 
		
	@Builder
	public Paging(int countPerPage, int currentPage, int totalRecordsCount) {
		this.countPerPage = countPerPage; 			//페이지당 보여줄 게시글 수 
		this.totalRecordsCount = totalRecordsCount; //db에 등록된 전체 게시글 수
		
		//전체 페이지 수  구하기 (5 = ( 130 + 30 - 1) / 30
		totalPageCount = (totalRecordsCount + countPerPage - 1) / countPerPage;
		
		//전달받은 현재 페이지가 1보다 작으면 현제페이지를 1페이지로 지정
		if (currentPage < 1) currentPage = 1;
		
		//전달받은 현재 페이지가 마지막 페이지보다 크면 현재페이지를 마지막 페이지로 지정
		if (currentPage > totalPageCount) currentPage = totalPageCount;
		
		this.currentPage = currentPage;
		
		//게시글 아래에 표시할 시작 블록번호와 끝 블록번호를 구하기
		// - 현재 위치한 블록 번호를 구하기
		currentBlock = (currentPage - 1) / pagePerBlock;

		// - 시작 블록 번호를 구하기
		startBlock = currentBlock * pagePerBlock + 1;
		
		// - 끝 블록 번호를 구하기
		endBlock = startBlock + pagePerBlock - 1;
		
		// - 끝 블록 번호가 전체 블록 수 번호보다 크면 전체 블록 수를 끝 블록 번호로 덮어씌운다.
		if (endBlock > totalPageCount) endBlock = totalPageCount;
		
		//현재 페이지에 표시할 게시글 시작번호와 끝 번호 구하기.
		endRecord = currentPage * countPerPage; 
		startRecord = endRecord - countPerPage + 1;
	}
}
