package com.wfb.user.model;

public class User {

	/**
	 * 用户userId
	 */
	private Integer userId;

	/**
	 * 用户telephone
	 */
	private String telephone;

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 生日
	 */
	private String birthday;
	
	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 邮箱是否验证(1:未验证，2:已验证)
	 */
	private String emailStatus;
	/**
	 * 用户密码
	 */
	private String password;
	
	private String paymentPassword;

	/**
	 * 扰码
	 */
	private String salt;

	/**
	 * 用户类型(1:普通用户;2:物流商;3:加盟商)
	 */
	private String userType;
	
	/**
	 * 身高
	 */
	private String height;
	
	/**
	 * 体重
	 */
	private String weight;

	/**
	 * 积分
	 */
	private Integer integral;
	
	/**
	 * 已使用积分
	 */
	private Integer usedIntegral;

	/**
	 * 性别12
	 */
	private String sex;

	/**
	 * 职业
	 */
	private String occupation;

	/**
	 * 是否企业认证(1:未认证; 2: 已认证)
	 */
	private String status;

	/**
	 * 注册时间
	 */
	private String createTime;

	/**
	 * 用户图片
	 */
	private String logoUrl;
	
	/**
	 * 注册来源（1pc,2Android,3ios,4wap）
	 */
	private String register;
	
	private Integer  province; //省编号
	
	private String provinceName; //省名称
	
	private Integer  city; //市编号
	
	private String cityName; //市名称
	
	private Integer  county; //区(县)编号
	
	private String countyName; //区县名称
	
	private String address;   //街道地址

	/**
	 * 优惠券数
	 */
	private Integer couponCount;
	
	/**
	 * 订单数
	 */
	private Integer orderCount;
	
	/**
	 * 订单总金额
	 */
	private String totalAmount;
	
	/**
	 * 可用余额
	 */
	private String availAsset;
	
	/**
	 * 微博
	 */
	private String weibo;
	
	/**
	 * 微信
	 */
	private String weixin;
	
	/**
	 * QQ
	 */
	private String qq;
	
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

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCounty() {
		return county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAvailAsset() {
		return availAsset;
	}

	public void setAvailAsset(String availAsset) {
		this.availAsset = availAsset;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getUsedIntegral() {
		return usedIntegral;
	}

	public void setUsedIntegral(Integer usedIntegral) {
		this.usedIntegral = usedIntegral;
	}

}
