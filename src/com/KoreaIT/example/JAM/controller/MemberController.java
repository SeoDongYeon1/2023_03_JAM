package com.KoreaIT.example.JAM.controller;

import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberController extends Controller{
	public void doJoin(String cmd) {
		System.out.println("==회원 가입==");

		String loginId = null;
		String loginPw = null;
		String name = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("필수 정보입니다.");
				continue;
			}

			SecSql sql = new SecSql();

			sql.append("SELECT COUNT(*) > 0");
			sql.append(" FROM `member`");
			sql.append(" WHERE loginId = ?", loginId);

			boolean isLoginIdDup = DBUtil.selectRowBooleanValue(conn, sql);

			if (isLoginIdDup) {
				System.err.println(loginId + "는(은) 이미 사용중인 아이디입니다.");
				continue;
			}

			System.out.println("사용 가능한 아이디입니다.");
			break;
		}

		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("필수 정보입니다.");
				continue;
			}

			while (true) {
				System.out.printf("로그인 비밀번호 확인 : ");
				loginPwConfirm = sc.nextLine().trim();

				if (loginPwConfirm.length() == 0) {
					System.out.println("필수 정보입니다.");
					continue;
				}
				break;
			}
			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
				continue;
			}

			System.out.println("비밀번호가 일치합니다.");
			break;
		}

		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();

			if (name.length() == 0) {
				System.out.println("필수 정보입니다.");
				continue;
			}
			break;
		}

		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member`");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", name);

		DBUtil.insert(conn, sql);

		System.out.println(name + "님 회원가입되었습니다.");
	}

}
