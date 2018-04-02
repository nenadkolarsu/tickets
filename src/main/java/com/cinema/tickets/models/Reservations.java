package com.cinema.tickets.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="reservations")
public class Reservations implements Serializable {
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
	private String name;
    private Integer row;
    private Integer seat;
    private String remark;
	private String action;
	private Date timestamp;
	private boolean status;
	private String reserve;
	
    @ManyToOne(optional = false)
    @JoinColumn(name="id_projection")
    private Projections projections; 
    
	@Value("${cinema.rows:test}")
	private long rows;
	@Value("${cinema.seats}")
	private long seats;
	
    public Reservations(){
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
//	@Column(name = "code")
////	@NotEmpty  
//    public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//	
    public Reservations(String name){
    	this.name = name;
    }
    
    // name
	@Column(name = "name")

	@NotNull
    @Size(min=2, message="Name should have at least 2 characters")	
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

    @NotNull
    @Min(1)
 //   @Max(10)
	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

    @NotNull
    @Min(1)
    @Max(10)	
	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	public Projections getProjections() {
		return projections;
	}

	public void setProjections(Projections projections) {
		this.projections = projections;
	}

	@Override
	public String toString() {
		return "Reservations [id=" + id + ", row=" + row + ", seat=" + seat + ", remark=" + remark + ", action="
				+ action + ", timestamp=" + timestamp + ", status=" + status + ", reserve=" + reserve + ", projections="
				+ projections + "]";
	}
	
	
    
}
