package com.cinema.tickets.repository;

import com.cinema.tickets.models.Projections;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectionsRepository extends JpaRepository<Projections, Long> {
	
	// Projections findByName(String projections);
	
	List<Projections> findAllByOrderByIdAsc();
	
    @Query("SELECT t FROM Projections t where t.projection_date >= CURRENT_DATE order by t.projection_date, t.projection_time")
    List<Projections> findProjectionsFromCurrentDate();
    
    //   @Query("SELECT t FROM Projections t where t.projection_date =  :currentDate")	 
    //   List<Projections> findProjectionsFromCurrentDate(@Param("currentDate") String currentDate);
}
