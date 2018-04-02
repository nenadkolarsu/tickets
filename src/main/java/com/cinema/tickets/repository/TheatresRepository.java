package com.cinema.tickets.repository;

import com.cinema.tickets.models.Theatres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatresRepository extends JpaRepository<Theatres, Long> {
	
	Theatres findByName(String theatres);
	
	List<Theatres> findAllByOrderByIdAsc();
}
