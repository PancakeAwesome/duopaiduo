package cn.digirun.admin.service.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/** 
 * @ClassName: Module 
 * @Description: 模块
 * @author 管东海
 *  
 */
@Entity(name = "digirun_adm_module")
@DynamicInsert
@DynamicUpdate
public class Module implements Serializable{

	private static final long serialVersionUID = 793905039500767513L;

	@Id
	private String id;
	private String name;
	private String icon;
	private String parent;
	
	@OneToMany(mappedBy="module")
	private Set<Authority> authorities = new HashSet<Authority>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
}
