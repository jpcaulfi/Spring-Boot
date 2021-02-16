package com.movietheater.MovieTheater.movies;

import com.movietheater.MovieTheater.showtimes.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieById(int id);

}
