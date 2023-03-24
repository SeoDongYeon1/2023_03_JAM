package com.KoreaIT.example.JAM;

import com.KoreaIT.example.JAM.exception.SQLErrorException;

public class Main {
	public static void main(String[] args) {
		try {
			new App().start();
		} catch(SQLErrorException e) { // 에러 시 문제있는 쿼리와 그 이유가 나온다.
			System.err.println(e.getMessage());
			e.getOrigin().printStackTrace();
		}
	}
}