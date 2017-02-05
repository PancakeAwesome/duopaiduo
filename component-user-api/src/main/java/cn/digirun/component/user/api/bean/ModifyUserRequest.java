package cn.digirun.component.user.api.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ModifyUserRequest
 * @Description: 更新用户信息请求
 * @author 管东海
 * 
 */
public class ModifyUserRequest implements Serializable {

	private static final long serialVersionUID = -5394021747613809042L;
	private String name;
	private String idCard;
	private String cellphone;
	private String address;
	private Date birthday;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
