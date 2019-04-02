package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.ch.dao")
@ServletComponentScan
public class ChApplication {

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

//	@Bean(initMethod = "migrate")
//	Flyway flyway() {
//		Flyway flyway = new Flyway();
//		flyway.setBaselineOnMigrate(true);
//		flyway.setDataSource(url, username, password);
//		return flyway;
//	}

	public static void main(String[] args) {
		SpringApplication.run(ChApplication.class, args);
	}

}
