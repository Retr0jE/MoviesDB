package ru.spring.Proektik.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.Proektik.Models.Role;
import ru.spring.Proektik.Models.User;
import ru.spring.Proektik.Repository.UserRepository;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String userView(Model model)
    {
        model.addAttribute("userList", userRepository.findAll());
        return "user/user-view";
    }

    @GetMapping("/edit/{id}")
    public String userEditView(@PathVariable(name = "id") Long id, Model model)
    {
        model.addAttribute("roleList", Role.values());
        model.addAttribute("userOne", userRepository.findById(id).orElseThrow());
        return "user/user-edit";
    }

    @PostMapping("/edit/{id}")
    public String userEdit(@PathVariable(name = "id") Long id,
                           @RequestParam String username,
                           @RequestParam(name = "roles[]") String[] userRoles,
                           Model model)
    {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);

        user.getRoles().clear();

        for (String roleOne : userRoles)
        {
            user.getRoles().add(Role.valueOf(roleOne));
        }

        userRepository.save(user);

        return "redirect:/user/";

    }

}
