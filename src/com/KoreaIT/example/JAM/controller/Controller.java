package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

public class Controller {
	protected Connection conn;
	protected Scanner sc;

	public void setScanner(Scanner sc) {
		this.sc = sc;
	}

	public void setCon(Connection conn) {
		this.conn = conn;
	}
}
