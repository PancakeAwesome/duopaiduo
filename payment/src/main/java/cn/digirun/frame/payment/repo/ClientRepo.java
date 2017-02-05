package cn.digirun.frame.payment.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.digirun.frame.payment.entity.Client;

public interface ClientRepo extends MongoRepository<Client, String>{

	Client findFirstByProjectName(String name);
}
