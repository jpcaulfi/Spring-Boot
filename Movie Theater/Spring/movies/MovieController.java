package com.movietheater.MovieTheater.movies;

import com.movietheater.MovieTheater.showtimes.Showtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class MovieController {

    @Autowired
    public MovieRepository movieRepository;

    @GetMapping("movies")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("movies/{movieid}")
    public List<Movie> getMovieByMovieId(@PathVariable(value = "movieid")
                                                           int id) {
        return movieRepository.findMovieById(id);
    }

}
