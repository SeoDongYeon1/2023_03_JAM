package com.KoreaIT.example.JAM;

public class Article extends Object{ // object는 모든 클래스의 부모(최상위 클래스)
	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;
	
	public Article(int id, String title, String body) {
		this.id = id; 
		this.title = title;
		this.body = body;
	}
	
	public Article(int id, String regDate, String updateDate, String title, String body) { // 넘겨받은 정보를 객체에 채워넣기
		this.id = id; 
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}


	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title
				+ ", body=" + body + "]";
	}
}
