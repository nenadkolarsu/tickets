package com.cinema.tickets.repository;

import com.cinema.tickets.models.Reservations;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
	
	List<Reservations> findAllByOrderByIdAsc();
    
    @Query("SELECT t.id FROM Reservations t where t.projections.id = :id_projection"
    		+ " and t.row = :row and t.seat = :seat") 
    Long findReservationByIdProjections(@Param("id_projection") Long id_projection,
    		@Param("row") Integer row,
    		@Param("seat") Integer seat);
    
    

}
