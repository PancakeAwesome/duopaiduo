package cn.digirun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.digirun.component.item.service.util.OSS.OSSConfig;

@SpringBootApplication
@ComponentScan(basePackages = { "cn.digirun" })
@EnableConfigurationProperties(OSSConfig.class)
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}