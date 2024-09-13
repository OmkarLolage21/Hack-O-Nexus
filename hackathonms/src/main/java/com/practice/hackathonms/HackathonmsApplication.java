package com.practice.hackathonms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HackathonmsApplication {

	public static void main(String[] args) {SpringApplication.run(HackathonmsApplication.class, args);}
}
