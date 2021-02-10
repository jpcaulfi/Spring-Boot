package com.movietheater.MovieTheater.tickets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("movies/{movieid}/showtimes/{showtimeid}/tickets")
    public Page<Ticket> getAllTicketsByShowtimeId(@PathVariable(value = "showtimeid")
                                                           int showtimeId, Pageable pageable) {
        return ticketRepository.findByShowtimeId(showtimeId, pageable);
    }

    //@PostMapping.....

}
