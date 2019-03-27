package com.pig4cloud.pig.storage;

import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnablePigFeignClients
public class PigStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigStorageApplication.class, args);
	}

}
