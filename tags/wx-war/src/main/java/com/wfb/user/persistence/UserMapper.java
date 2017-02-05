package com.wfb.user.persistence;

import com.wfb.user.model.User;

public interface UserMapper {
	
	User getUserDetail(Integer userId);
}
