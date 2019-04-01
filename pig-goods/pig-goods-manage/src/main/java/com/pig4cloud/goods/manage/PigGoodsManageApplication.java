package com.pig4cloud.goods.manage;

import com.pig4cloud.goods.manage.config.FlywayConfig;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringCloudApplication
@EnablePigResourceServer
public class PigGoodsManageApplication {

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
		SpringApplication.run(PigGoodsManageApplication.class, args);
	}

}
