package com.movietheater.MovieTheater.showtimes;

import com.movietheater.MovieTheater.tickets.Ticket;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="showtimes")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtimeid")
    private int id;

//    @Id
    @Column(name = "movieid")
    private int movieId;

    @Column(name = "time")
    private String time;

    @OneToMany(mappedBy = "showtimeId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Ticket> tickets = new HashSet<>();

    public Showtime() {

    }

    public Showtime(int id, int movieId, String time, Set<Ticket> tickets) {
        this.id = id;
        this.movieId = movieId;
        this.time = time;
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showtime showtime = (Showtime) o;
        return id == showtime.id && movieId == showtime.movieId && Objects.equals(time, showtime.time) && Objects.equals(tickets, showtime.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, time, tickets);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Showtime{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", time='" + time + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
