package ru.spring.Proektik.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.Proektik.Models.Cinema;
import ru.spring.Proektik.Models.Movies;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {


//   Cinema findById(Integer id);


}
