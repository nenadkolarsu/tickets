package com.cinema.tickets.repository;
import com.cinema.tickets.models.Cinemas;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemasRepository extends JpaRepository<Cinemas, Long> {
	
	Cinemas findByName(String cinemas);
//	Cinemas findOne(Long id);
	// delete(Long id);
	List<Cinemas> findAllByOrderByIdAsc();
	// Cinemas findOne(Long id);
}
