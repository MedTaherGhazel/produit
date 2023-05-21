package com.iset.produit.controllers;

import com.iset.produit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/signup")
        public String saveUser(@RequestParam(name="username") String username,
                               @RequestParam(name="password") String pass,
                               @RequestParam(name="confirmPassword") String confirmPassword)
{
    userService.saveUser(username,pass,confirmPassword);
    return "redirect:/showCreate";
}

}
