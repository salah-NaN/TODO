package OMC_prueba.TODO.controllers;

import OMC_prueba.TODO.models.UserModel;
import OMC_prueba.TODO.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model){

        UserModel newUser = new UserModel();
        model.addAttribute("newUser", newUser);

        return "register";
    }

    @PostMapping("/register")
    public String registerPost( @ModelAttribute("newUser") UserModel newUser,
                                Model model
                                ){


        // comprobar que el usuario no exista
        UserModel potentialUser = this.userService.getUserByUsername(newUser.getUsername());
        if(potentialUser != null){
            // volvemos al formulario
            return "redirect:/register?error";
        }
        // encriptamos la contraseña
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        this.userService.saveUser(newUser);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }




}
