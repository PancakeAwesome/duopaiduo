package cn.digirun.component.order.service.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.digirun.component.order.model.AreaModel;
import cn.digirun.component.order.service.manager.IAreaManager;
import cn.digirun.component.order.service.repository.AreaRepository;

/**
 * 地区Serv
 * @author 管超
 *
 */
@Service
@Transactional
public class AreaManager implements IAreaManager {
	@Autowired
	private AreaRepository areaRepository;
	
	@Override
	public List<AreaModel> getAreas(Integer pid) {
		return areaRepository.findByFPid(pid);
	}

}
