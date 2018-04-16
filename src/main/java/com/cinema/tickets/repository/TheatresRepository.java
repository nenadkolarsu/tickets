package com.cinema.tickets.repository;

import com.cinema.tickets.models.Theatres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TheatresRepository extends JpaRepository<Theatres, Long> {
	
	Theatres findByName(String theatres);
	
	List<Theatres> findAllByOrderByIdAsc();
	    
    @Query("SELECT t.rows FROM Theatres t where t.id = :id_theatre") 
    Integer findNumberOfRowsForTheatre(@Param("id_theatre") Long id_theatre);

    @Query("SELECT t.seats FROM Theatres t where t.id = :id_theatre") 
    Integer findNumberOfSeatsForTheatre(@Param("id_theatre") Long id_theatre);    
	
}
