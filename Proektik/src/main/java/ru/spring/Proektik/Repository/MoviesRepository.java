package ru.spring.Proektik.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.Proektik.Models.Movies;
import ru.spring.Proektik.Models.Zoo;

import java.util.List;

public interface MoviesRepository extends CrudRepository<Movies, Long> {

    public List<Movies> findByGenre(String genre);
    public List<Movies> findByNameContaining(String name);
}
