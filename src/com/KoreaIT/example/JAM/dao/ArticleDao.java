package com.KoreaIT.example.JAM.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class ArticleDao {
	private Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	public int doWrite(String title, String body) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title); // ? 안에 title 값이 들어감
		sql.append(", `body` = ?", body); // ? 안에 body 값이 들어감
		return DBUtil.insert(conn, sql);
	}

	public int getArticlesCountById(int id) {
		SecSql sql = new SecSql(); // 게시글 유무 판단
		sql.append("SELECT COUNT(*)");
		sql.append(" FROM article");
		sql.append(" WHERE id = ?", id);
		
		return DBUtil.selectRowIntValue(conn, sql);
	}

	public Map<String, Object> getArticleById(int id) {
		SecSql sql = new SecSql(); // 게시글 유무 판단
		sql.append("SELECT *");
		sql.append(" FROM article");
		sql.append(" WHERE id = ?", id);
		
		return DBUtil.selectRow(conn, sql);
	}

	public void doDelete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);
		
		DBUtil.delete(conn, sql);
	}

	public void doModify(int id, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append(" SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(" WHERE id = ?", id);

		DBUtil.update(conn, sql);
	}

	public List<Article> getArticles() {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC;");

		List<Map<String, Object>> articlesListMap = DBUtil.selectRows(conn, sql);
		
		List<Article> articles = new ArrayList<>();
		
		for (Map<String, Object> articleMap : articlesListMap) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}

}
