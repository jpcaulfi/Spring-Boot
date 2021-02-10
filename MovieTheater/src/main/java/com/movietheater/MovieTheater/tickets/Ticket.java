package com.movietheater.MovieTheater.tickets;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketid")
    private int id;

//    @Id
    @Column(name = "movieid")
    private int movieId;

//    @Id
    @Column(name = "showtimeid")
    private int showtimeId;

    @Column(name = "quantity")
    private int quantity;

    public Ticket() {

    }

    public Ticket(int id, int movieId, int showtimeId, int quantity) {
        this.id = id;
        this.movieId = movieId;
        this.showtimeId = showtimeId;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && movieId == ticket.movieId && showtimeId == ticket.showtimeId && quantity == ticket.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, showtimeId, quantity);
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

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", showtimeId=" + showtimeId +
                ", quantity=" + quantity +
                '}';
    }
}
