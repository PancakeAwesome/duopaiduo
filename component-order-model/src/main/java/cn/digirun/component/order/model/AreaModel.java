package cn.digirun.component.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;

@Entity(name = "t_domestic_area")
@DynamicInsert
@DynamicUpdate
public class AreaModel {

	private static final long serialVersionUID = 1L;
	/**
	 * id 自增长
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 地区名称
	 */
	private String fName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public Integer getfPid() {
		return fPid;
	}

	public void setfPid(Integer fPid) {
		this.fPid = fPid;
	}

	private Integer fPid;
	
		
}
