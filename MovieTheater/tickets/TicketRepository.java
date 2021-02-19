package com.movietheater.MovieTheater.tickets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByMovieId(int movieId);
    List<Ticket> findByShowtimeId(int showtimeId);
}
