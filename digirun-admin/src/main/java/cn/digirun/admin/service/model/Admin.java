package cn.digirun.admin.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/** 
 * @ClassName: Admin 
 * @Description: 管理员
 * @author 管东海
 *  
 */
@Entity(name = "digirun_adm_admin")
@DynamicInsert
@DynamicUpdate
public class Admin implements Serializable, UserDetails {

	private static final long serialVersionUID = 5714558816464670480L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private Boolean enabled;

	@ManyToMany
	@JoinTable(name = "digirun_adm_admin_role_ref", inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") , joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "id") )
	private Set<Role> roles = new HashSet<Role>();

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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Iterator<Role> roles = getRoles().iterator();

		List<Authority> authorities = new ArrayList<Authority>();

		while (roles.hasNext()) {

			authorities.addAll(roles.next().getAuthorities());
		}

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return getEnabled();
	}
}
