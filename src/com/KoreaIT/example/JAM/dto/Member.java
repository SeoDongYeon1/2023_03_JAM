package com.KoreaIT.example.JAM.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Member {
	public int id;
	public LocalDateTime regDate;
	public LocalDateTime updateDate;
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(int id, LocalDateTime regDate, LocalDateTime updateDate, String loginId, String loginPw, String name) { // 넘겨받은 정보를 객체에 채워넣기
		this.id = id; 
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
	
	public Member(Map<String, Object> memberMap) {
		this.id = (int) memberMap.get("id");
		this.regDate = (LocalDateTime) memberMap.get("regDate");
		this.updateDate = (LocalDateTime) memberMap.get("updateDate");
		this.loginId = (String) memberMap.get("loginId");
		this.loginPw = (String) memberMap.get("loginPw");
		this.name = (String) memberMap.get("name");
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", loginId=" + loginId + ", name=" + name + "]";
	}
}
