package com.driver;

import com.driver.model.CountryName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class Vpn {
	public static void main(String[] args) {
		SpringApplication.run(Vpn.class, args);
	}

}
