package com.pig4cloud.storage;

import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class PigStorageApplication {
	public static void main(String[] args) {
		SpringApplication.run(PigStorageApplication.class, args);
	}

}
