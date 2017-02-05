package com.wfb.user.persistence;

import com.wfb.user.model.RoleModel;
import com.wfb.utils.IConverter;

public interface RoleMapper extends IConverter<RoleModel> {
	String getActionName(Integer actionId);
}
