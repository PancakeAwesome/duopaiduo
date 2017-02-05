package cn.digirun.component.item.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class Application extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
}
