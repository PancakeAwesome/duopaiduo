package com.wfb.common.service;

import java.util.List;
import com.wfb.common.model.DomesticArea;

public interface IDomesticAreaService {

	List<DomesticArea> getDoesticAreaList(Integer parentId);

	DomesticArea findById(Integer id);
}
