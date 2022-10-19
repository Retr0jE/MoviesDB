package ru.spring.Proektik.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.Proektik.Models.Movies;
import ru.spring.Proektik.Models.Zoo;
import ru.spring.Proektik.Repository.MoviesRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    MoviesRepository moviesRepository;


    @GetMapping("")
    public String movies(Model model)
    {
        Iterable<Movies> listMovies = moviesRepository.findAll();
        model.addAttribute("listKino",listMovies);
        return "movies/index";
    }

    @GetMapping("/add")
    public String moviesAddView(Model model, Movies movies)
    {
        return "movies/movies-add";
    }

    @PostMapping("/add")
    public String moviesAdd(@Valid Movies movies,
                 BindingResult bindingResult,
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
        moviesRepository.save(movies);
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
