package com.pepper.learn.netty.io.P3_NIO;

public class Client {

	public static final Integer PORT = 10010;

	public static void main(String[] args) {
		
		new Thread(new TimeClient("127.0.0.1", PORT), "NIO-TimeClient-001").start();
	}
}
