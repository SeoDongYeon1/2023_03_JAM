package com.KoreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.controller.ArticleController;
import com.KoreaIT.example.JAM.controller.MemberController;

public class App {

	public void start() {
		System.out.println("=프로그램 시작==");
		Container.sc = new Scanner(System.in);
		
		Container.init();
		
		while (true) {
			System.out.print("명령어 ) ");
			String cmd = Container.sc.nextLine().trim();

			Connection conn = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			String url = "jdbc:mysql://127.0.0.1:3306/JAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

			try {
				conn = DriverManager.getConnection(url, "root", "");
				Container.conn =conn;

				int actionResult = action(cmd);

				if (actionResult == -1) {
					System.out.println("프로그램을 종료합니다");
					break;
				}

			} catch (SQLException e) {
				System.out.println("@@@@@@에러 1@@@@@@:" + e);
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private int action(String cmd) {
		ArticleController articleController = Container.articleController;
		MemberController memberController = Container.memberController;
		
		if(cmd.length()==0) {
			System.out.println("명령어를 입력하세요.");
			return 0;
		}

		if (cmd.equals("exit")) {
			return -1;
		}

		if (cmd.equals("article write")) {
			articleController.doWrite();
		} 
		else if (cmd.startsWith("article list")) {
			articleController.showList(cmd);
		} 
		else if (cmd.startsWith("article modify ")) {
			articleController.doModify(cmd);
		}
		else if (cmd.startsWith("article detail ")) {
			articleController.showDetail(cmd);
		}
		else if (cmd.startsWith("article delete ")) {
			articleController.doDelete(cmd);
		}
		else if (cmd.equals("member join")) {
			memberController.doJoin();
		}
		else if (cmd.equals("member login")) {
			memberController.doLogin();
		}
		else if (cmd.equals("member logout")) {
			memberController.doLogout();
		}
		else if (cmd.equals("member profile")) {
			memberController.showProfile();
		}
		else {
			System.out.println("존재하지 않는 명령어입니다.");
		}
		return 0;
	}

}