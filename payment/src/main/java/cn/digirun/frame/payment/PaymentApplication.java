package cn.digirun.frame.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import cn.digirun.frame.payment.alipay.util.AlipayConfig;
import cn.digirun.frame.payment.unionpay.util.UnionpayConfig;
import cn.digirun.frame.payment.wxpay.util.WxpayConfig;

/** 
 * @ClassName: PaymentApplication 
 * @Description: 程序入口
 * @author 管东海
 *  
 */
@SpringBootApplication
@EnableConfigurationProperties({AlipayConfig.class, WxpayConfig.class,UnionpayConfig.class})
public class PaymentApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
		/*ApplicationContext context = SpringApplication.run(PaymentApplication.class, args);
		PaymentRepo payment = context.getBean(PaymentRepo.class);*/
		/*
		ApplicationContext context = SpringApplication.run(PaymentApplication.class, args);
		MongoTemplate mt = context.getBean(MongoTemplate.class);
		ClientRepo repo = context.getBean(ClientRepo.class);
		
		Client cc = new Client();
		cc.setProjectName("wufanbao");
		cc.setProjectId("wufanbaoApiId");
		cc.setProjectKey("wufanban20160501KEY");
		repo.save(cc);*/
		
		  
//		Client c = new Client();
//		c.setName("aaa");
//		c.setSalt("ccc");
//		
//		repo.save(c);
//		repo.findFirstByName("aaaaaa");
//		System.out.println();
//		context.getBeanDefinitionNames();
	}
}
