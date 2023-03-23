package com.KoreaIT.example.JAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;
import com.KoreaIT.example.JAM.util.Util;

public class ArticleController extends Controller{
	public void doWrite() {
		System.out.println("==게시물 작성==");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title); // ? 안에 title 값이 들어감
		sql.append(", `body` = ?", body); // ? 안에 body 값이 들어감

		int id = DBUtil.insert(conn, sql);

		System.out.println(id + "번 글이 생성 되었습니다");
	}

	public void showList() {
		System.out.println("==게시물 목록==");

		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC;");

		List<Map<String, Object>> articlesListMap = DBUtil.selectRows(conn, sql);

		for (Map<String, Object> articleMap : articlesListMap) {
			articles.add(new Article(articleMap));
		}

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}

		System.out.println("번호   /   제목");

		for (Article article : articles) {
			System.out.printf("%3d   /   %s\n", article.id, article.title);
		}
	}

	public void doModify(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);

		System.out.println("==게시물 수정==");
		SecSql sql = new SecSql(); // 게시글 유무 판단
		sql.append("SELECT COUNT(*)");
		sql.append(" FROM article");
		sql.append(" WHERE id = ?", id);
		
		int articlesCount = DBUtil.selectRowIntValue(conn, sql);
		
		if(articlesCount==0) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. \n", id);
			return;
		}
		
		System.out.printf("새 제목 : ");
		String title = sc.nextLine();
		System.out.printf("새 내용 : ");
		String body = sc.nextLine();

		sql = new SecSql();

		sql.append("UPDATE article");
		sql.append(" SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(" WHERE id = ?", id);

		DBUtil.update(conn, sql);

		System.out.println(id + "번 글이 수정 되었습니다");
	}

	public void showDetail(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		System.out.println("==게시물 상세보기==");
		
		SecSql sql = new SecSql(); // 게시글 유무 판단
		sql.append("SELECT *");
		sql.append(" FROM article");
		sql.append(" WHERE id = ?", id);
		
		Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);
		
		if(articleMap.isEmpty()) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. \n", id);
			return;
		}
		
		Article article = new Article(articleMap);
		
		System.out.printf("번호 : %d \n", article.id);
		System.out.printf("제목 : %s \n", article.title);
		System.out.printf("내용 : %s \n", article.body);
		System.out.printf("등록날짜 : %s \n", Util.getNowDateTimeStr(article.regDate));
		System.out.printf("수정날짜 : %s \n", Util.getNowDateTimeStr(article.updateDate));
	}

	public void doDelete(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		System.out.println("==게시물 삭제==");
		
		SecSql sql = new SecSql(); // 게시글 유무 판단
		sql.append("SELECT COUNT(*)");
		sql.append(" FROM article");
		sql.append(" WHERE id = ?", id);
		
		int articlesCount = DBUtil.selectRowIntValue(conn, sql);
		
		if(articlesCount==0) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. \n", id);
			return;
		}
		
		sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);
		
		DBUtil.delete(conn, sql);
		
		System.out.printf("%d번 게시글이 삭제 되었습니다. \n", id);
	}
	
}
