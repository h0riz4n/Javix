package com.Javix.JavixWeb.controller;

import com.Javix.JavixWeb.models.Player;
import com.Javix.JavixWeb.repo.PlayerRepo;
import com.Javix.JavixWeb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/{id}")
    public String registerPage(@PathVariable("id") String id, @RequestParam("login") String login, @RequestParam("password") String password, Model model) {
        if (playerService.userValidData(login, password)) {
            Player player = new Player(Long.valueOf(id), login, password);
            playerRepo.save(player);
            return "forward:/" + id + "/registered_page";
        } else {
            model.addAttribute("text", "Пожалуйста, вводите данные корректно.\nВаш логин должен быть меньше 25 символов и в пароле должны отсутствовать кириллические символы.");
            return "register_page";
        }
    }

    @RequestMapping(value = "/{id}/registered_page")
    public String mainGetPage(@PathVariable("id") long id, Model model) {
        if (playerRepo.existsByTgId(id)) {
            return "registered_page";
        } else {
            model.addAttribute("error", "Error");
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String mainUserPage(@PathVariable(value = "id") String id, Model model) {
        if (playerRepo.existsByTgId(Long.valueOf(id))) {
            return "forward:/" + id + "/registered_page";
        } else {
            model.addAttribute("text", "Пожалуйста, зарегистрируйтесь, чтобы пользоваться ботом");
            return "register_page";
        }
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("error", "Error");
        return "error";
    }
}
