package com.cinema.tickets.repository;

import com.cinema.tickets.models.Projections;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionsRepository extends JpaRepository<Projections, Long> {
	
	// Projections findByName(String projections);
	
	List<Projections> findAllByOrderByIdAsc();
}
