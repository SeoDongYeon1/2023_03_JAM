package com.KoreaIT.example.JAM.test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.KoreaIT.example.JAM.Article;
//
//public class JDBCSelectTest {
//	public static void main(String[] args) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		List<Article> articles = new ArrayList<>();
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			String url = "jdbc:mysql://127.0.0.1:3306/JAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
//
//			conn = DriverManager.getConnection(url, "root", "");
//			System.out.println("연결 성공!");
//
//			String sql = "SELECT *";
//			sql += " FROM article";
//			sql += " ORDER BY id DESC;";
//
//			System.out.println(sql);
//
//			pstmt = conn.prepareStatement(sql);
//
//			rs = pstmt.executeQuery(sql);
//			
//			while(rs.next()) { // while(rs.next())는 다음 데이터가 없을때까지 반복
//				int id = rs.getInt("id");
//				LocalDateTime regDate = rs.("regDate");
//				LocalDateTime updateDate = rs.getString("updateDate");
//				String title = rs.getString("title");
//				String body = rs.getString("body");
//				
//				Article article = new Article(id, regDate, updateDate, title, body);
//				articles.add(article);
//			}
//			
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패");
//		} catch (SQLException e) {
//			System.out.println("에러 : " + e);
//		} finally {
//			try { // close는 역순이 좋다
//				if (rs != null && !rs.isClosed()) {
//					rs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (pstmt != null && !pstmt.isClosed()) {
//					pstmt.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null && !conn.isClosed()) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println("실행결과 : " + articles);
//
//	}
//}