package com.example.codeE;

import com.example.codeE.constant.Constant;
import com.example.codeE.judge.Server;
import com.example.codeE.judge.handlers.JudgeHandler;
import com.example.codeE.judge.handlers.SpringBootHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.net.InetSocketAddress;

@SpringBootApplication
public class CodeEApplication {
	@Autowired
	private JudgeHandler judgeHandler;
	@Autowired
	private SpringBootHandler springBootHandler;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CodeEApplication.class, args);
		InetSocketAddress address1 = new InetSocketAddress(Constant.BRIDGED_HOST, Constant.BRIDGED_SPRING_BOOT_PORT);
		InetSocketAddress address2 = new InetSocketAddress(Constant.BRIDGED_HOST, Constant.BRIDGED_JUDGE_PORT);

		Server server1 = new Server(address1, context.getBean(SpringBootHandler.class));
		Server server2 = new Server(address2, context.getBean(JudgeHandler.class));
		Thread t1 = new Thread(() -> {
			try {
				server1.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				server2.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
