package cn.digirun.component.order.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "t_user_order_address")
@DynamicInsert
@DynamicUpdate
public class UserOrderAddress implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id 自增长
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 省编号
	 */
	private Integer  provinceId; 
	
	/**
	 * 省名称
	 */
	private String  provinceName; 
	
	/**
	 * 市编号
	 */
	private Integer  cityId; 
	
	/**
	 * 市名称
	 */
	private String  cityName; 
	
	/**
	 * 区(县)编号
	 */
	private Integer  countyId; 
	
	/**
	 * 区(县)名称
	 */
	private String  countyName; 
	
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

	/**
	 * 是否可用(1:可用; 2:删除)
	 */
	private Integer status;
	
	private Date createTime;
	
	/**
	 * 是否默认地址(1:否，2:是)
	 */
	private Integer isDef;
	
	public UserOrderAddress(){
	    
	}
	
	

	public UserOrderAddress(String provinceName, String cityName, String countyName, String userName, String phone,String address) {
		super();
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.countyName = countyName;
		this.userName = userName;
		this.phone = phone;
		this.address = address;
	}



	public UserOrderAddress(Long id){
		this.id = id;
	}
	
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
