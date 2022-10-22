package ru.spring.Proektik.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.Proektik.Models.Role;
import ru.spring.Proektik.Models.User;
import ru.spring.Proektik.Repository.UserRepository;

import javax.persistence.CollectionTable;
import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String regView(User user)
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user,
                          Model model
    )
    {
        if(userRepository.findByUsername(user.getUsername())!= null)
        {
            model.addAttribute(
                    "error",
                    "Гавно блядь");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return "redirect:/login";
    }


}
