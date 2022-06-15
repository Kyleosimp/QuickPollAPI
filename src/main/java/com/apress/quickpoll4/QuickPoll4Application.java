package com.apress.quickpoll4;

import com.apress.quickpoll4.controller.PollController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackageClasses = PollController.class)
public class QuickPoll4Application {

	public static void main(String[] args) {
		SpringApplication.run(QuickPoll4Application.class, args);
	}

}
