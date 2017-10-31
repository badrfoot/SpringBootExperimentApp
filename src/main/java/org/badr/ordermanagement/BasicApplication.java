package org.badr.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BasicApplication {	

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}
	
}
