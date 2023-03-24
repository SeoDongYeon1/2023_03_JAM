package com.KoreaIT.example.JAM.service;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.dao.MemberDao;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public boolean isLoginIdDup(String loginId) {
		return memberDao.isLoginIdDup(loginId);
	}

	public void doJoin(String loginId, String loginPw, String name) {
		memberDao.doJoin(loginId, loginPw, name);
	}

	public Member getMemberByloginId(String loginId) {
		return memberDao.getMemberByloginId(loginId);
	}

}
