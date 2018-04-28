package com.shuxin.foodsstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class FoodsstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodsstoreApplication.class, args);
	}
}
