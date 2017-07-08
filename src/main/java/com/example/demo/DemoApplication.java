package com.example.demo;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		try {
			push_demo.push();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
