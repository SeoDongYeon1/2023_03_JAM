package com.KoreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.dao.ArticleDao;

public class ArticleService {
	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body) {
		return articleDao.doWrite(title, body);
	}

	public int getArticlesCountById(int id) {
		return articleDao.getArticlesCountById(id);
	}

	public Map<String, Object> getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void doDelete(int id) {
		articleDao.doDelete(id);
	}

	public void doModify(int id, String title, String body) {
		articleDao.doModify(id, title, body);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

}