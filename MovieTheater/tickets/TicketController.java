package com.movietheater.MovieTheater.tickets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("tickets")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("movies/{movieid}/showtimes/{showtimeid}/tickets")
    public List<Ticket> getAllTicketsByShowtimeId(@PathVariable(value = "showtimeid")
                                                  int showtimeId) {
        return ticketRepository.findByShowtimeId(showtimeId);
    }

    @PostMapping("tickets")
    Ticket insertTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

}
