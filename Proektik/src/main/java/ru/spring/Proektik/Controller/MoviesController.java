package ru.spring.Proektik.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.Proektik.Models.Cinema;
import ru.spring.Proektik.Models.Movies;
import ru.spring.Proektik.Models.Session;
import ru.spring.Proektik.Models.Zoo;
import ru.spring.Proektik.Repository.CinemaRepository;
import ru.spring.Proektik.Repository.MoviesRepository;
import ru.spring.Proektik.Repository.SessionRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    MoviesRepository moviesRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    SessionRepository sessionRepository;


    @GetMapping("")
    public String movies(Model model)
    {
        Iterable<Movies> listMovies = moviesRepository.findAll();
        model.addAttribute("listKino",listMovies);

        return "movies/index";
    }


    @GetMapping("/add2")
    public String moviescinemaAddView(Model model, Movies movies)
    {
        Iterable<Cinema> cinemak = cinemaRepository.findAll();
        List<Cinema> cinemas = new ArrayList<>();
        for (Cinema cinema:cinemak)
            cinemas.add(cinema);
        model.addAttribute("cinemak", cinemas);
        Iterable<Movies> films = moviesRepository.findAll();
        model.addAttribute("movies", films);
        return "movies/moviescinemas-add";
    }

    @PostMapping("/add2")
    public String moviescinemaAdd(@RequestParam Long movies, @RequestParam(name = "cinemas") Long idc, Model model)
    {
        Cinema cinema2 = cinemaRepository.findById(idc).orElseThrow();
        Movies movies2 = moviesRepository.findById(movies).orElseThrow();

//        university2.getStudents().add(student2);
//        universityRepository.save(university2)

        cinema2.getMovies().add(movies2);
        cinemaRepository.save(cinema2);
        return "redirect:/movies";
    }

    @GetMapping("/add")
    public String moviesAddView(Model model, Movies movies)
    {
        Iterable<Session> sessione = sessionRepository.findAll();
        model.addAttribute("sessions",sessione);
        return "movies/movies-add";
    }

    @GetMapping("/onetoone")
    public String onetooneAddView(Model model, Movies movies)
    {
        Iterable<Cinema> cinemak = cinemaRepository.findAll();
        model.addAttribute("cinema", cinemak);
        return "movies/onetoone";
    }

    @PostMapping("/onetoone")
    public String onetooneAdd(@RequestParam Integer hall, @RequestParam String time, @RequestParam(name = "cinema") Long idc, Model model)
    {
        Cinema cinema = cinemaRepository.findById(idc).orElseThrow();
        Session session = new Session();

        session.setHall(hall);
        session.setTime(time);
        session.setCinema(cinema);

        sessionRepository.save(session);
        return "redirect:/movies";
    }

    @GetMapping("/filter")
    public String moviesFilter(@RequestParam(name = "searchName") String name,
                                Model model)
    {
        List<Movies> film = moviesRepository.findByNameContaining(name);

        model.addAttribute("kinchik", film);

        return "movies/movies-filter";
    }

    @PostMapping("/add")
    public String moviesAdd(@Valid Movies movies,
                            BindingResult bindingResult,
                            @RequestParam(name = "sessions") Long session_id,

//            @RequestParam String name,
//                         @RequestParam Integer year,
//                         @RequestParam String genre,
//                         @RequestParam String country,
//                            @RequestParam Integer budget,
                            Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "movies/movies-add";
        }

        Session session = sessionRepository.findById(session_id).orElseThrow();

        movies.setSession(session);

        moviesRepository.save(movies);
        return "redirect:/movies";
    }

    @GetMapping("/filtergenre")
    public String moviesFilterGenre(
                               @RequestParam(name = "searchGenre") String genre, Model model)
    {

        List<Movies> film2 = moviesRepository.findByGenre(genre);
        model.addAttribute("kinchik", film2);
        return "movies/movies-filter";
    }

    @GetMapping("/detail/{id}")
    public String moviesDetail(@PathVariable Long id,
                            Model model)
    {
        Optional<Movies> kino =moviesRepository.findById(id);
        ArrayList<Movies> res = new ArrayList<>();

        kino.ifPresent(res::add);

        model.addAttribute("kino", res);
        return "/movies/movies-detail";
    }

    @GetMapping("/del/{id}")
    public String moviesDel(@PathVariable Long id)
    {
        moviesRepository.deleteById(id);
        return "redirect:/movies";
    }
    @GetMapping("/edit/{id}")
    public String moviesEditView(@PathVariable Long id, Model model, Movies movies)
    {
        movies = moviesRepository.findById(id).orElseThrow();
        model.addAttribute("movies", movies);
        return "/movies/movies-edit";
    }


    @PostMapping("/edit/{id}")
    public String moviesEdit(@PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam Integer year,
                                 @RequestParam String genre,
                                 @RequestParam String country,
                                 @RequestParam Integer budget,
                              Model model, @Valid Movies movies, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "movies/movies-edit";
        }
        movies = moviesRepository.findById(id).orElseThrow();
        movies.setName(name);
        movies.setYear(year);
        movies.setGenre(genre);
        movies.setCountry(country);
        movies.setBudget(budget);

        moviesRepository.save(movies);

        return "redirect:/movies/detail/" + movies.getId();
    }
}
