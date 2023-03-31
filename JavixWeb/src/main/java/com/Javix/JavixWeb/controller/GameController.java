package com.Javix.JavixWeb.controller;

import com.Javix.JavixWeb.models.Numbers;
import com.Javix.JavixWeb.models.TicTacToe;
import com.Javix.JavixWeb.repo.NumbersRepo;
import com.Javix.JavixWeb.repo.TicTacToeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private TicTacToeRepo ticTacToeRepo;

    @Autowired
    private NumbersRepo numbersRepo;

    @GetMapping(value = "/tic_tac_toe")
    public String ticTacToeGame(@RequestParam(value = "id") long id) {
        if (!ticTacToeRepo.existsByTgId(id)) {
            TicTacToe ticTacToe = new TicTacToe(id);
            ticTacToeRepo.save(ticTacToe);
        }
        return "tic_tac_toe";
    }

    @GetMapping("/number_game")
    public String numbersGame(@RequestParam(value = "id") long id) {
        if (!numbersRepo.existsByTgId(id)) {
            Numbers numbers = new Numbers(id);
            numbersRepo.save(numbers);
        }
        return "2048";
    }
}
