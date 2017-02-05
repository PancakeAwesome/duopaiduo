package cn.digirun.frame.payment.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

/** 
 * @ClassName: Application 
 * @Description: 程序入口
 * @author 管东海
 *  
 */
@SpringBootApplication
public class Application {

	private static final String QUEUE_PAYMENT_PAY = "payment_pay";

	@JmsListener(destination = QUEUE_PAYMENT_PAY)
	public void paymentPay(String data) {
		System.out.println("接收到服务器发送的报文："+data);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args).getBean(JmsTemplate.class).receiveAndConvert(QUEUE_PAYMENT_PAY);

	}
}
