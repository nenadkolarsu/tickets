/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cinema.tickets.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "projections")

public class Projections implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
//    @Column(name = "id_cinema")
//    private Long idVrstaDokumenta;    
    
    @ManyToOne(optional = false)
    @JoinColumn(name="id_theatre")
    private Theatres theatres;   
    
    @ManyToOne(optional = false)
    @JoinColumn(name="id_movie")
    private Movies movies;   
    
    
    private String code;
//    private String name;
    
    private String remark;
	private String action;
	private Date timestamp;
	private boolean status;
	private String reserve;
	private String movie;
	
    @Column(name = "projection_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date projection_date;
    
//    @DateTimeFormat(iso=ISO.TIME)
//    @Column(name = "projection_time")
//    @Temporal(TemporalType.TIME)
//    @DateTimeFormat(pattern = "HH-mm-ss") 
    
    private String projection_time;
        
    @Column(name = "projection_datetime")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="dd.MM.yyyy hh:mm")
    private Date projection_datetime;



    public Projections() {
    }

    public Projections(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Theatres getTheatres() {
        return theatres;
    }
 
    public void setTheatres(Theatres theatres ) {
        this.theatres = theatres;
    }

	@Column(name = "code")

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
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

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public Date getProjection_date() {
		return projection_date;
	}

	public void setProjection_date(Date projection_date) {
		this.projection_date = projection_date;
	}

//	public Time getProjection_time() {
//		return projection_time;
//	}
//
//	public void setProjection_time(Time projection_time) {
//		this.projection_time = projection_time;
//	}
	
	
	
	public Date getProjection_datetime() {
		return projection_datetime;
	}

	public String getProjection_time() {
		return projection_time;
	}

	public void setProjection_time(String projection_time) {
		this.projection_time = projection_time;
	}

	public void setProjection_datetime(Date projection_datetime) {
		this.projection_datetime = projection_datetime;
	}

	@Override
	public String toString() {
		return "Theatres [id=" + id + ", theatres=" + theatres + ",  +  remark="
				+ remark + ", action=" + action + ", timestamp=" + timestamp + ", status=" + status + ", reserve="
				+ reserve + "]";
	}

	public Movies getMovies() {
		return movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}
    

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projections")
    private List<Reservations> reservations = new ArrayList<>();

	public List<Reservations> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservations> reservations) {
		this.reservations = reservations;
	}

    
}
