package ru.spring.Proektik.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index()
    {

        return "Index";
    }


    @GetMapping("/main/")
    public String mainGet(@RequestParam(name = "var", required = false, defaultValue = "Bubbamainer") Double dick,
            @RequestParam (name = "digit", required = false) Double digit,
            @RequestParam (required = false)  Double length,
                          @RequestParam (name = "upa", required = false) String plus,

            Model model)
    {
        if(Objects.equals(plus, "+")) {
            length = Double.valueOf(dick + digit);
        } else if (Objects.equals(plus, "-")) {
            length = Double.valueOf(dick - digit);

        } else if (Objects.equals(plus, "*")) {
            length = Double.valueOf(dick * digit);
        } else if (Objects.equals(plus, "/")) {
            length = Double.valueOf(dick / digit);
        }

//        model.addAttribute("map", digit);
//        model.addAttribute("name", dick);
        model.addAttribute("map", length);
        return "Home";
    }

    @PostMapping("/main/")
    public String mainPost(@RequestParam(name = "var",
            required = false,
            defaultValue = "Bubbamainer") Double dick,
                       @RequestParam (name = "digit",required = false) Double digit,
                       @RequestParam (required = false)  Double length,
                           @RequestParam (name = "upa", required = false) String plus,
                       Model model)
    {
        if(Objects.equals(plus, "+")) {
            length = Double.valueOf(dick + digit);
        } else if (Objects.equals(plus, "-")) {
            length = Double.valueOf(dick - digit);

        } else if (Objects.equals(plus, "*")) {
            length = Double.valueOf(dick * digit);
        } else if (Objects.equals(plus, "/")) {
            length = Double.valueOf(dick / digit);
        }

//        model.addAttribute("map", digit);
//        model.addAttribute("name", dick);
        model.addAttribute("map", length);
        return "Home";
    }
}




























