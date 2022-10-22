package ru.spring.Proektik.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cinema {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name_cinema;

    @Column
    private String address;

    @ManyToMany
    @JoinTable(name = "movies_cinema",
                joinColumns = @JoinColumn(name = "cinema_id"),
                inverseJoinColumns = @JoinColumn(name = "movies_id"))
      private List<Movies> movies;

    @OneToOne(optional = true, mappedBy = "cinema")
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName_cinema() {
        return name_cinema;
    }

    public void setName_cinema(String name_cinema) {
        this.name_cinema = name_cinema;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }
}
