package ru.spring.Proektik.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.Proektik.Models.Zoo;
import ru.spring.Proektik.Repository.ZooRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/zoo")
public class ZooController {

    @Autowired
    ZooRepository zooRepository;

    @GetMapping("")
    public String zoo(Model model)
    {
        Iterable<Zoo> listZoo = zooRepository.findAll();
        model.addAttribute("listAnimal",listZoo);
        return "zoo/index";
    }

    @GetMapping("/add")
    public String zooAddView(Model model, Zoo zoo)
    {
        return "zoo/zoo-add";
    }

    @PostMapping("/add")
    public String zooAdd(@Valid Zoo zoo,
                         BindingResult bindingResult,
//                         @RequestParam String name,
//                         @RequestParam String description,
//                         @RequestParam Integer age,
//                         @RequestParam Integer mass,
                          Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "zoo/zoo-add";
        }

        zooRepository.save(zoo);
        return "redirect:/zoo";
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam(name = "searchName") String name, Model model)
    {
        List<Zoo> animals = zooRepository.findByNameContaining(name);
        model.addAttribute("anim", animals);
        return "zoo/zoo-filter";
    }

    @GetMapping("/detail/{id}")
    public String zooDetail(@PathVariable Long id,
            Model model)
    {
        Optional<Zoo> animal =zooRepository.findById(id);
        ArrayList<Zoo> res = new ArrayList<>();

        animal.ifPresent(res::add);

        model.addAttribute("animal", res);
        return "/zoo/zoo-detail";
    }

   @GetMapping("/del/{id}")
    public String zooDel(@PathVariable Long id)
   {
       zooRepository.deleteById(id);
       return "redirect:/zoo";
   }
    @GetMapping("/edit/{id}")
    public String zooEdit(@PathVariable Long id, Model model)
    {
        Zoo animal = zooRepository.findById(id).orElseThrow();
        model.addAttribute("animal", animal);
        return "/zoo/zoo-edit";
    }


    @PostMapping("/edit/{id}")
    public String zooEditView(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Integer age,
                              @RequestParam Integer mass,
                              Model model)
    {
        Zoo animal = zooRepository.findById(id).orElseThrow();
        animal.setName(name);
        animal.setDescription(description);
        animal.setAge(age);
        animal.setMass(mass);

        zooRepository.save(animal);

        return "redirect:/zoo/detail/" + animal.getId();
    }




}
