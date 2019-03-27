package com.pig4cloud.pig.goods.manage;

import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnablePigFeignClients
@SpringCloudApplication
public class PigGoodsManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigGoodsManageApplication.class, args);
	}

}

