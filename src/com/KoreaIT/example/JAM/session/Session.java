package com.KoreaIT.example.JAM.session;

import com.KoreaIT.example.JAM.Member;

public class Session {

	public Member loginedMember;
	
	public void logout() {
		loginedMember = null;
	}

	public void login(Member member) {
		loginedMember = member;
	}

	public boolean isLogined() {
		if(loginedMember!=null) {
			return true;
		}
		return false;
	}
	
}
