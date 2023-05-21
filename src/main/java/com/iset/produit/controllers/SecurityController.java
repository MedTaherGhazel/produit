package com.iset.produit.controllers;

import com.iset.produit.entities.Produit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {
    @GetMapping("/accessDenied")
    public String geterror() {
        return "accessDenied";
    }

    @PostMapping("/accessDenied")
    public String posterror() {
        return "accessDenied";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/signup")
    public String signup(ModelMap modelMap)
    {
        modelMap.addAttribute("user", new UserForm());
        return"login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException
    {
        request.logout();
        return "redirect:/login";
    }


}
