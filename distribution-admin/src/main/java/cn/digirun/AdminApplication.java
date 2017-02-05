package cn.digirun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cn.digirun.component.order.service.DBRepositoryImpl;


/** 
 * @ClassName: PaymentApplication 
 * @Description: 程序入口
 * @author 管东海
 *  
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass=DBRepositoryImpl.class)
public class AdminApplication {
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AdminApplication.class, args);
}}
