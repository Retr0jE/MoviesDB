package ru.spring.Proektik.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Integer hall;

    @Column
    private String time;


    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER)
    private Collection<Movies> movies;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="cinema_id")
    private Cinema cinema;

    public Session(){

    }


    public Session(Integer hall, String time, Collection<Movies> movies, Cinema cinema) {
        this.hall = hall;
        this.time = time;
        this.movies = movies;
        this.cinema = cinema;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getHall() {
        return hall;
    }

    public void setHall(Integer hall) {
        this.hall = hall;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Collection<Movies> getMovies() {
        return movies;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void setMovies(Collection<Movies> movies) {
        this.movies = movies;
    }
}
