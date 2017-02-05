package cn.digirun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/** 
 * @ClassName: PaymentApplication 
 * @Description: 程序入口
 * @author 管东海
 *  
 */
@SpringBootApplication
public class WebApplication {
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WebApplication.class, args);
}}
