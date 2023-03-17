package com.KoreaIT.example.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Article> articles = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		System.out.println("==프로그램 시작==");
		
		while(true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();
			
			if(command.equals("exit")) {
				break;
			}
			
			if(command.length()==0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if(command.equals("article write")) {
				System.out.println("==게시물 작성==");
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				articles.add(article);
				
				// System.out.println(article); // ex) Article [id=1, title=제목, body=내용]
				System.out.printf("%d번글이 생성되었습니다.\n", article.id);
				lastArticleId++;
			}
			else if(command.equals("article list")) {
				System.out.println("==게시물 목록==");
				
				if(articles.size()==0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}		
				System.out.println("번호 /   제목");
				for(int i = articles.size()-1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d   /   %s  \n", article.id, article.title);
				}
			}
			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}
		System.out.println("==프로그램 종료==");
		sc.close();
	}
}