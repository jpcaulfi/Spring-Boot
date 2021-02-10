package com.movietheater.MovieTheater.showtimes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    Page<Showtime> findByMovieId(int movieId, Pageable pageable);
}
