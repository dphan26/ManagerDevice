package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Controller;


import com.example.demo.subcontroller.SubHomeLandingComponent;
import com.example.landing.HomeLandingComponent;

@SpringBootApplication
public class DemoSpringbootGrandleApplication {

	public static void main(String[] args) {
		// ApplicationContext chính là container, chứa toàn bộ các Bean
		ApplicationContext context = SpringApplication.run(DemoSpringbootGrandleApplication.class, args);
		// Khi chạy xong, lúc này context sẽ chứa các Bean có đánh
		// dấu @Component

		// HomeController is a bean, mark by @Controller
		HomeController homeController = context.getBean(HomeController.class);
		// In ra để xem thử nó là gì
		System.out.println("Instance: " + homeController);

		/** SubHomeLandingComponent is a bean, Class is mark by @Component */
		SubHomeLandingComponent subHomeLandingComponent = context.getBean(SubHomeLandingComponent.class);
		// In ra để xem thử nó là gì
		System.out.println("Instance subHomeLandingComponent: " + subHomeLandingComponent);

		/**
		 * ProductController is not a bean, Class isn't mark by @Component
		 * or @Controller
		 */
		// ProductController productController =
		// context.getBean(ProductController.class);
		// System.out.println("Instance productController: " + productController);

		/**
		 * HomeLandingComponent is not a bean, Package not less level than
		 * DemoSpringbootGrandleApplication Class Spring Không quét các class trong
		 * package ở cùng cấp với DemoSpringbootGrandleApplication
		 */
		// HomeLandingComponent homeLandingComponent =
		// context.getBean(HomeLandingComponent.class);
		// System.out.println("Instance homeLandingComponent: " + homeLandingComponent);
	}

}
