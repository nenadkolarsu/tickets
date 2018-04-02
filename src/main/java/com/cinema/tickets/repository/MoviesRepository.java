package com.cinema.tickets.repository;
import com.cinema.tickets.models.Movies;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movies, Long> {
	
	Movies findByName(String movies);
	List<Movies> findAllByOrderByIdAsc();

}
