package cn.digirun.component.order.api.bean;

import java.util.Date;

/**
 * 保存用户订单地址请求类
 * @author Administrator
 *
 */
public class SaveUserOrderAddressReq{
	
	private Long id;
	
	/**
	 * 省编号
	 */
	private Integer  provinceId; 
	
	private String provinceName;
	
	/**
	 * 市编号
	 */
	private Integer  cityId; 
	
	private String cityName;
	
	/**
	 * 区(县)编号
	 */
	private Integer  countyId; 
	
	private String countyName;
	
	/**
	 * 邮编
	 */
	private String postCode;
	
	/**
	 * 用户姓名
	 */
	private Long userId;
	
	/**
	 * 联系人姓名
	 */
	private String userName;
	
	/**
	 * 手机号
	 */
	private String phone;
	
	
	/**
	 * 地址
	 */
	private String address;

	private Integer status;
	
	private Date createTime;
	
	/**
	 * 是否默认地址(1:否，2:是)
	 */
	private Integer isDef;
	
	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Integer getIsDef() {
		return isDef;
	}

	public void setIsDef(Integer isDef) {
		this.isDef = isDef;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public Integer getCountyId() {
		return countyId;
	}


	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}


	public String getPostCode() {
		return postCode;
	}


	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
