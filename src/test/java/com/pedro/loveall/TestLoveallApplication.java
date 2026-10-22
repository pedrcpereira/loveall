package com.pedro.loveall;

import org.springframework.boot.SpringApplication;

public class TestLoveallApplication {

	public static void main(String[] args) {
		SpringApplication.from(LoveallApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
