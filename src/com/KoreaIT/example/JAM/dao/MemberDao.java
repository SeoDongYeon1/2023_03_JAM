package com.KoreaIT.example.JAM.dao;

import java.util.Map;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberDao {
	
	public MemberDao() {
	}

	public boolean isLoginIdDup(String loginId) {
		SecSql sql = new SecSql();

		sql.append("SELECT COUNT(*) > 0");
		sql.append(" FROM `member`");
		sql.append(" WHERE loginId = ?", loginId);

		return DBUtil.selectRowBooleanValue(Container.conn, sql);
	}

	public void doJoin(String loginId, String loginPw, String name) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);

		DBUtil.insert(Container.conn, sql);
	}

	public Member getMemberByloginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append(" FROM `member`");
		sql.append(" WHERE loginId = ?", loginId);
		
		Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		Member member = new Member(memberMap);
		return member;
	}

	public Member getMemberByMemberId(int memberId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append(" FROM `member`");
		sql.append(" WHERE id = ?", memberId);
		
		Map<String, Object> memberMap = DBUtil.selectRow(Container.conn, sql);
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		Member member = new Member(memberMap);
		return member;
	}
}
