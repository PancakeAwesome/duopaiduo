package cn.digirun.component.user.api.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName: LoginRequest
 * @Description: 登录请求
 * @author 管东海
 * 
 */
public class LoginRequest implements Serializable {

	private static final long serialVersionUID = 7311799070876765031L;
	@NotEmpty(message = "用户名不允许为空")
	private String username;
	@NotEmpty(message = "密码不允许为空")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
