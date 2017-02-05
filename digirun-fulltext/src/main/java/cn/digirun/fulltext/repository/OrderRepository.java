package cn.digirun.fulltext.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import cn.digirun.fulltext.model.Order;

/**
 * 订单elasticsearch数据服务
 * @author 张海东
 *
 */
public interface OrderRepository extends ElasticsearchRepository<Order, Long>{

}
