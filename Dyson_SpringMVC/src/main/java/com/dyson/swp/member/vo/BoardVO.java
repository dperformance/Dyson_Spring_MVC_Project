package com.dyson.swp.member.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO { // Oracle -> T_BOARD 테이블 (게시판 테이블)

	private int  	r_num, b_seq;
	private String 	b_writer, b_title, b_content, b_pw;
	private int 	b_hit, b_group, b_step, b_lev;
	private String 	b_bname, b_filename;
	private Date 	b_cre_date;
	private String 	b_cre_ip;
}
