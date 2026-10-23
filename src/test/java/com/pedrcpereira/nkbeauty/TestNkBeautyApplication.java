package com.pedrcpereira.nkbeauty;

import org.springframework.boot.SpringApplication;

public class TestNkBeautyApplication {

	public static void main(String[] args) {
		SpringApplication.from(NkBeautyApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
