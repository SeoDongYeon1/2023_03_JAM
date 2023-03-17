package com.KoreaIT.example.JAM;

public class Article extends Object{ // object는 모든 클래스의 부모(최상위 클래스)
	public int id;
	public String title;
	public String body;
	
	public Article(int id, String title, String body) { // 넘겨받은 정보를 객체에 채워넣기
		this.id = id; 
		this.title = title;
		this.body = body;
	}

	@Override // object 오버라이드 받은것
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", body=" + body + "]"; // this가 생략되어있음.
	}
}
