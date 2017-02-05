package cn.digirun.frame.payment.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.digirun.frame.payment.entity.Payment;

public interface PaymentRepo extends MongoRepository<Payment, String>{

	Payment findByOrderNo(String orderNo);
}
