package com.movietheater.MovieTheater.showtimes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class ShowtimeController {

    @Autowired
    public ShowtimeRepository showtimeRepository;

    @GetMapping("showtimes")
    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    @GetMapping("movies/{movieid}/showtimes")
    public List<Showtime> getAllShowtimesByMovieId(@PathVariable (value = "movieid")
                                                   int movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }
}
