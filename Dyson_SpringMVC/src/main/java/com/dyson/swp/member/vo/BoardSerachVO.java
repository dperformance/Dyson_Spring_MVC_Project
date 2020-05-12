package com.dyson.swp.member.vo;

import lombok.Data;

@Data
public class BoardSerachVO { // 검색어로 게시판을 검색했을때 사용되는 VO 
	
	private String conSelect, searchData;
}
