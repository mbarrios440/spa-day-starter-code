package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("users", UserData.getAll());

        if (user.getPassword().equals(verify)) {
            UserData.add(user);
            return "user/index";
        } else {
            model.addAttribute("error", true);
            return "user/add";
        }
    }

    @GetMapping("user/{userId}")
    public String displayEditForm(Model model, @PathVariable int userId) {

        model.addAttribute("user", UserData.getById(userId));
        return "user/edit";
    }

}
