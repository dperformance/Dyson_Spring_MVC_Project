package com.dyson.swp.member.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CommVO { // Oracle -> T_COMMENT 테이블 (게시판 댓글 테이블)

	private int 	c_seq, c_step, c_lev;
	private String 	c_writer, c_content, c_pw, c_bname;
	private Date 	c_cre_date;
	private String 	c_cre_ip;
	private int 	b_seq;
}
