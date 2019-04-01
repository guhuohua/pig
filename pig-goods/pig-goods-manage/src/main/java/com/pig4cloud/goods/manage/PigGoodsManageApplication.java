package com.pig4cloud.goods.manage;

import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
@EnablePigResourceServer
public class PigGoodsManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigGoodsManageApplication.class, args);
	}

}
