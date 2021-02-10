package com.movietheater.MovieTheater.movies;

import com.movietheater.MovieTheater.showtimes.Showtime;
import com.movietheater.MovieTheater.tickets.Ticket;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieid")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private int rating;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "movieId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Showtime> showtimes = new HashSet<>();

    public Movie() {

    }

    public Movie(int id, String title, int rating, String image, Set<Showtime> showtimes) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.image = image;
        this.showtimes = showtimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && rating == movie.rating && Objects.equals(title, movie.title) && Objects.equals(image, movie.image) && Objects.equals(showtimes, movie.showtimes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, rating, image, showtimes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(Set<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", image='" + image + '\'' +
                ", showtimes=" + showtimes +
                '}';
    }
}
