package com.pig4cloud.pig.shop.manage;

import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnablePigFeignClients
@SpringCloudApplication
@EnablePigResourceServer
public class PigShopManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigShopManageApplication.class, args);
	}

}
