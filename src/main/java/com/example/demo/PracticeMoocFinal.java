package com.example.demo;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;




@SpringBootApplication
public class PracticeMoocFinal {

	public static void main(String[] args) {
		// ApplicationContext chính là container, chứa toàn bộ các Bean
		ApplicationContext context = SpringApplication.run(PracticeMoocFinal.class, args);
		
	}

}
