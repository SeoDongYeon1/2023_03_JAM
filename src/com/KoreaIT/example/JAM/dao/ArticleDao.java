package com.KoreaIT.example.JAM.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.container.Container;
import com.KoreaIT.example.JAM.dto.Article;
import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class ArticleDao {
	public ArticleDao() {
	}

	public int doWrite(int memberId, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", memberId = ?", memberId); // ? 안에 memberId 값이 들어감
		sql.append(", title = ?", title); // ? 안에 title 값이 들어감
		sql.append(", `body` = ?", body); // ? 안에 body 값이 들어감
		return DBUtil.insert(Container.conn, sql);
	}

	public int getArticlesCountById(int id) {
		SecSql sql = new SecSql(); // 게시글 유무 판단
		sql.append("SELECT COUNT(*)");
		sql.append(" FROM article");
		sql.append(" WHERE id = ?", id);
		
		return DBUtil.selectRowIntValue(Container.conn, sql);
	}

	public Map<String, Object> getArticleById(int id) {
		SecSql sql = new SecSql(); // 게시글 유무 판단
		sql.append("SELECT article.*, member.name AS extra__writer");
		sql.append("FROM article");
		sql.append("INNER JOIN `member`");
		sql.append("ON article.memberId = `member`.id");
		sql.append("WHERE article.id = ?", id);
		
		return DBUtil.selectRow(Container.conn, sql);
	}

	public void doDelete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);
		
		DBUtil.delete(Container.conn, sql);
	}

	public void doModify(int id, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append(" SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(" WHERE id = ?", id);

		DBUtil.update(Container.conn, sql);
	}

	public List<Article> getArticles() {
		SecSql sql = new SecSql();

		sql.append("SELECT article.*, member.name AS extra__writer");
		sql.append("FROM article");
		sql.append("INNER JOIN `member`");
		sql.append("ON article.memberId = `member`.id");
		sql.append("ORDER BY id DESC;");

		List<Map<String, Object>> articlesListMap = DBUtil.selectRows(Container.conn, sql);
		
		List<Article> articles = new ArrayList<>();
		
		for (Map<String, Object> articleMap : articlesListMap) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}
	
}
