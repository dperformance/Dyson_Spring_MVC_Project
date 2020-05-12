package com.dyson.swp.member.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO { // Oracle -> T_MEMBER 테이블 (사용자 정보 테이블)
	
	private int 	mem_idx;
	private String 	mem_email, mem_pw, mem_name, mem_images, mem_cre_ip;
	private Date 	mem_cre_dt, mem_mod_dt;
	private String 	email_auth, role, MEM_SESSIONKEY, MEM_SESSIONLIMIT;
}
