package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.service.MemberService;

public class MemberController extends Controller{
	private MemberService memberService;
	
	public MemberController(Connection conn, Scanner sc) {
		super(sc); // 부모 생성자 호출
		memberService = new MemberService(conn);
	}
	
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
			
			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

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
		
		memberService.doJoin(loginId, loginPw, name);

		System.out.println(name + "님 회원가입되었습니다.");
	}

}
