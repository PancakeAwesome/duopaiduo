package cn.digirun.component.user.api.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName: RegisterRequest
 * @Description: 注册请求
 * @author zhanghd
 * @date 2016 8 2 
 */
public class RegisterRequest implements Serializable {

	private static final long serialVersionUID = 7311799070876765031L;
	@NotEmpty(message = "用户名不允许为空")
	private String username;
	@NotEmpty(message = "密码不允许为空")
	private String password;
	private Integer provinceId;
	private Integer cityId;
    private String cityName;
	private String provinceName;
	private Integer userType;
    private String promoter;
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

	public String getPromoter() {
		return promoter;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	
}
