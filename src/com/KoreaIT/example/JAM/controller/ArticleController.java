package com.KoreaIT.example.JAM.controller;

import java.util.List;

import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.dto.Article;
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
		int memberId = Container.session.loginedMember.id;
		
		int id = articleService.doWrite(memberId, title, body);

		System.out.println(id + "번 글이 생성 되었습니다");
	}

	public void showList(String cmd) {
		System.out.println("==게시물 목록==");
		
		String[] cmdBits = cmd.split(" ");
		
		int page = 1;
		String searchKeyword = null;
		
		// 몇페이지?
		if(cmdBits.length >= 3) {
			page = Integer.parseInt(cmdBits[2]);
		}
		// 검색어
		if(cmdBits.length >= 4) {
			searchKeyword = cmdBits[3];
		}
		// 한 페이지에 5개씩
		int itemsInAPage = 5;
		
		List<Article> articles = articleService.getForPrintArticles(page, itemsInAPage, searchKeyword);

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}
		
		System.out.println("번호   /   제목   /   작성자   / 조회수");
		for (Article article : articles) {
			System.out.printf("%3d   /   %s  /    %s     / %d \n", article.id, article.title, article.extra__writer, article.hit);
		}
	}

	public void doModify(String cmd) {
		if(Container.session.isLogined()==false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		int id = Integer.parseInt(cmd.split(" ")[2]);

		System.out.println("==게시물 수정==");
		
		Article article = articleService.getArticleById(id);
		
		if(article==null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. \n", id);
			return;
		}
		
		if (article.memberId != Container.session.loginedMember.id) {
			System.out.println("게시글에 대한 권한이 없습니다");
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
		
		articleService.IncreaseHit(id);
		
		Article article = articleService.getArticleById(id);
		
		if(article==null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. \n", id);
			return;
		}
		System.out.printf("번호 : %d \n", article.id);
		System.out.printf("제목 : %s \n", article.title);
		System.out.printf("내용 : %s \n", article.body);
		System.out.printf("작성자 : %s \n", article.extra__writer);
		System.out.printf("조회수 : %d \n", article .hit);
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
		
		Article article = articleService.getArticleById(id);
		
		if(article==null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다. \n", id);
			return;
		}
		
		if (article.memberId != Container.session.loginedMember.id) {
			System.out.println("게시글에 대한 권한이 없습니다");
			return;
		}
		
		articleService.doDelete(id);
		
		System.out.printf("%d번 게시글이 삭제 되었습니다. \n", id);
	}
	
}
