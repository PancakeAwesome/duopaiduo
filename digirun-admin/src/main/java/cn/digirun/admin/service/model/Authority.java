package cn.digirun.admin.service.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

/** 
 * @ClassName: Authority 
 * @Description: 权限
 * @author 管东海
 *  
 */
@Entity(name = "digirun_adm_authority")
@DynamicInsert
@DynamicUpdate
public class Authority implements Serializable,GrantedAuthority {

	private static final long serialVersionUID = 4075892377011606675L;

	@Id
	private String id;
	private String code;
	private String name;
	private String icon;
	@ManyToOne
	private Module module;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getAuthority() {
		return getCode();
	}
}
