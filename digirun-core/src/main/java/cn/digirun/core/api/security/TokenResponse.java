package cn.digirun.core.api.security;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

/** 
 * @ClassName: TokenResponse 
 * @Description: 令牌
 * @author 管东海
 *  
 */
@JSONType(ignores={"user","lastAccessTime"})
public class TokenResponse implements Serializable {

	private static final long serialVersionUID = -7902009114299234828L;

	private String token;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastAccessTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date expireTime;

	private Long userId;
	private Long promoterId;
	private TokenUser user;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public TokenUser getUser() {
		return user;
	}

	public void setUser(TokenUser user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(Long promoterId) {
		this.promoterId = promoterId;
	}


	
}
