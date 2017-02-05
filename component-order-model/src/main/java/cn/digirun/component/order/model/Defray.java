package cn.digirun.component.order.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.auditing.CurrentDateTimeProvider;

/**
 * 支付类型
 * @author Administrator
 *
 */
@Entity(name = "t_defray")
@DynamicInsert
@DynamicUpdate
public class Defray implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id 自增长
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 支付方式名称(到付,在线支付)
	 */
	private String name;
	
	/**
	 * 是否可用(1:可用; 2:删除)
	 */
	private Integer status;
	
	private Date createTime;
	
	public Defray(){
	    
	}
	
	public Defray(Long id){
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
