package com.pig4cloud.shop.manage;

import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
@EnablePigResourceServer
public class PigShopManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigShopManageApplication.class, args);
	}

}
