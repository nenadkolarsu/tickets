package com.cinema.tickets.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="cinemas")
public class Cinemas implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
//	private static final long serialVersionUID = -4361643849564698178L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
    private String code;
    private String name;
    private String remark;
	private String action;
	private Date timestamp;
	private boolean status;
	private String reserve;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cinemas")
    private List<Theatres> theatres = new ArrayList<>();
    
    public Cinemas(){
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
	@Column(name = "code")
	@NotEmpty  
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
    public Cinemas(String name){
    	this.name = name;
    }
    
    // name
	@Column(name = "name")
	@NotEmpty
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
 	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "action")
	public String getAkcija() {
		return action;
	}

	public void setAkcija(String action) {
		this.action = action;
	}
	
	@Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	public Date getTimestamp() {
		return timestamp;
	}
    @PrePersist
    protected void onCreate() {
    	timestamp = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
    	timestamp = new Date();
    }  
    
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(columnDefinition="tinyint(1) default 1")
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

//	@Column(columnDefinition="tinyint(1) default 1")
//	public boolean isAktivan() {
//		return action;
//	}
//
//	public void setAktivan(boolean aktivan) {
//		this.aktivan = aktivan;
//	}


	@Override
	public String toString() {
		return "Types of documents [id=" + id + ", name=" + name + ", code=" + code + ", remark=" + remark + ", action=" + action
				+ ", timestamp=" + timestamp +  "]";
	}

	public List<Theatres> getTheatres() {
		return theatres;
	}

	public void setTheatres(List<Theatres> theatres) {
		this.theatres = theatres;
	}

	@PreRemove
	private void preRemove() {
	    if (!theatres.isEmpty()) {
	        try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	

    
    
    
}
