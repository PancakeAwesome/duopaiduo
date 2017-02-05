package cn.digirun.component.order.service.manager;

import java.util.List;

import cn.digirun.component.order.model.AreaModel;

/**
 * 地区Serv
 * @author 管超
 *
 */
public interface IAreaManager {
	List<AreaModel> getAreas(Integer pid);
}
