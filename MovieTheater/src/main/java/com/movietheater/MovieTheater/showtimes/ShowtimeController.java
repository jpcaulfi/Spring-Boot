package com.movietheater.MovieTheater.showtimes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class ShowtimeController {

    @Autowired
    public ShowtimeRepository showtimeRepository;

    @GetMapping("movies/{movieid}/showtimes")
    public Page<Showtime> getAllShowtimesByMovieId(@PathVariable (value = "movieid")
                                                   int movieId, Pageable pageable) {
        return showtimeRepository.findByMovieId(movieId, pageable);
    }
}
