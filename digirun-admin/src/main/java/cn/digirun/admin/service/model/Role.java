package cn.digirun.admin.service.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/** 
 * @ClassName: Role 
 * @Description: 角色
 * @author 管东海
 *  
 */
@Entity(name = "digirun_adm_role")
@DynamicInsert
@DynamicUpdate
public class Role implements Serializable {

	private static final long serialVersionUID = 3361731628981875265L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String icon;
	private String name;
	private String remark;

	@ManyToMany
	@JoinTable(name = "digirun_adm_role_auth_ref", inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id") , joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
	private Set<Authority> authorities = new HashSet<Authority>();

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
}
