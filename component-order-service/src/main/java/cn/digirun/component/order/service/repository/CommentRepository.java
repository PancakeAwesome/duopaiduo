package cn.digirun.component.order.service.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.digirun.component.order.model.RemarkModel;

public interface CommentRepository extends PagingAndSortingRepository<RemarkModel, Long>{

}
