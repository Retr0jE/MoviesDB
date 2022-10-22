package ru.spring.Proektik.Models;



import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Введите название фильма")
    @Size(min = 1, max = 70, message = "Мин 1 символ, максимум - 70")
    private String name;

    @Min(value = 1887, message = "Самый первый фильм вышел в 1888 году, ниже незя")
    @Max(value = 2023, message = "Ну сейчас какой год? выше незя")
    @NotNull(message = "ну введи плз")
    private Integer year;

    @NotBlank(message = "Давай введи")
    private String genre;

    @NotEmpty(message = "Страну тоже надо")
    private String country;

    @Positive(message ="Бюджет не может быть отрицательным")
    @NotNull(message = "Э")
    private Integer budget;

    @ManyToMany
    @JoinTable(name = "movies_cinema",
            joinColumns = @JoinColumn(name = "movies_id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_id"))
    private List<Cinema> cinema;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Session session;

    public Movies()
    {

    }

    public Movies(String name, Integer year, String genre, String country, Integer budget)
    {
       this.name = name;
       this.year = year;
       this.genre = genre;
       this.country = country;
       this.budget = budget;
    }

    public List<Cinema> getCinema() {
        return cinema;
    }

    public void setCinema(List<Cinema> cinema) {
        this.cinema = cinema;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
