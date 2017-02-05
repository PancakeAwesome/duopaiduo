package cn.digirun.fulltext.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.digirun.fulltext.model.ShoppingCar;

/**
 * 购物车elasticsearch数据服务
 * @author gdh
 *
 */
public interface ShoppingCarRepository extends ElasticsearchRepository<ShoppingCar, Long>{

}
