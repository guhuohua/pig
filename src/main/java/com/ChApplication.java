package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.ch.dao")
@ServletComponentScan
@EnableAsync
@EnableJms
public class ChApplication {



	public static void main(String[] args) {
		SpringApplication.run(ChApplication.class, args);
	}

}
