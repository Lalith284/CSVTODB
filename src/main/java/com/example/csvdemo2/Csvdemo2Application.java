package com.example.csvdemo2;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableBatchProcessing 
@EnableScheduling 
public class Csvdemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Csvdemo2Application.class, args);
	}
}
