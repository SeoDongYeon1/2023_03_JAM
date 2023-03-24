package com.KoreaIT.example.JAM.controller;

import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.service.ArticleService;
import com.KoreaIT.example.JAM.util.Util;

public class ArticleController extends Controller{
	
	private ArticleService articleService;
	
	public ArticleController() {
		articleService = Container.articleService;	
	}

	public void doWrite() {
		if(Container.session.isLogined()==false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		System.out.println("==게시물 작성==");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		int id = articleService.doWrite(title, body);

		System.out.println(id + "번 글이 생성 되었습니다");
	}

	public void showList() {
		System.out.println("==게시물 목록==");

		List<Article> articles = articleService.getArticles();

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
		if(Container.session.isLogined()==false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		int id = Integer.parseInt(cmd.split(" ")[2]);

		System.out.println("==게시물 수정==");
		
		int articlesCount = articleService.getArticlesCountById(id);
		
		if(articlesCount==0) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. \n", id);
			return;
		}
		
		System.out.printf("새 제목 : ");
		String title = sc.nextLine();
		System.out.printf("새 내용 : ");
		String body = sc.nextLine();
		
		articleService.doModify(id, title, body);

		System.out.println(id + "번 글이 수정 되었습니다");
	}

	public void showDetail(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		System.out.println("==게시물 상세보기==");
		
		Map<String, Object> articleMap = articleService.getArticleById(id);
		
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
		if(Container.session.isLogined()==false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		int id = Integer.parseInt(cmd.split(" ")[2]);
		System.out.println("==게시물 삭제==");
		
		int articlesCount = articleService.getArticlesCountById(id);
		
		if(articlesCount==0) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. \n", id);
			return;
		}
		
		articleService.doDelete(id);
		
		System.out.printf("%d번 게시글이 삭제 되었습니다. \n", id);
	}
	
}
