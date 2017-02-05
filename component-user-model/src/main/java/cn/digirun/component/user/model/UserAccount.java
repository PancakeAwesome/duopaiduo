package cn.digirun.component.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.alibaba.fastjson.annotation.JSONType;

import cn.digirun.core.api.security.TokenUser;

@Entity(name = "t_user_account")
@DynamicInsert
@DynamicUpdate
@JSONType(ignores = { "password","salt","id"})
public class UserAccount implements Serializable,TokenUser {

	private static final long serialVersionUID = 3355766306392939325L;

	public UserAccount() {
		super();
	}

	public UserAccount(String username, String password, User user) {
		super();
		this.username = username;
		this.password = password;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String salt;
	private Long promoterId;//推广人id
    private String promoterCode;//推广码
    private Integer userType;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date lastLoginTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerTime;
	@ColumnDefault(value = "0")
	private UserAccountEnable enable;

	@OneToOne(fetch=FetchType.LAZY)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public UserAccountEnable getEnable() {
		return enable;
	}

	public void setEnable(UserAccountEnable enable) {
		this.enable = enable;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(Long promoterId) {
		this.promoterId = promoterId;
	}

	public String getPromoterCode() {
		return promoterCode;
	}

	public void setPromoterCode(String promoterCode) {
		this.promoterCode = promoterCode;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	
	
}
