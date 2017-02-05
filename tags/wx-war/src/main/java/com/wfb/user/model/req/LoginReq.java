package com.wfb.user.model.req;

import org.hibernate.validator.constraints.NotBlank;

import com.jrzmq.core.rest.RestApiFieldAnn;

/**
 * 用户登录
 * @author wj
 *
 */
public class LoginReq {

	@RestApiFieldAnn(title="手机号", required=true)
	@NotBlank(message="手机号不可以为空")
	private String telephone;
	
	@RestApiFieldAnn(title="密码", required=true)
	@NotBlank(message="密码不可以为空")
	private String password;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
