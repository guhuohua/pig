package com;

import com.ch.config.FlywayConfig;
import org.flywaydb.core.Flyway;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.ch.dao")
@ServletComponentScan
@EnableAsync
public class ChApplication {

	@Autowired
	FlywayConfig flywayConfig;

	@Bean(initMethod = "migrate")
	Flyway flyway() {
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate(true);
		flyway.setDataSource(flywayConfig.getUrl(), flywayConfig.getUsername(), flywayConfig.getPassword());
		return flyway;
	}

	public static void main(String[] args) {
		SpringApplication.run(ChApplication.class, args);
	}

}
