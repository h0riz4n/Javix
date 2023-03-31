package com.Javix.JavixWeb.api;

import com.Javix.JavixWeb.models.Numbers;
import com.Javix.JavixWeb.models.TicTacToe;
import com.Javix.JavixWeb.repo.NumbersRepo;
import com.Javix.JavixWeb.repo.TicTacToeRepo;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class ApiStatsController {

    @Autowired
    private TicTacToeRepo ticTacToeRepo;

    @Autowired
    private NumbersRepo numbersRepo;

    @GetMapping("/getStats/tic_tac_toe")
    public ResponseEntity<TicTacToe> getTicTacToeStats(@RequestParam(value = "id", defaultValue = "1") @NonNull long id) {
        if (ticTacToeRepo.existsByTgId(id)) {
            TicTacToe ticTacToe = ticTacToeRepo.getByTgId(id);
            return new ResponseEntity<>(ticTacToe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getStats/2048")
    public ResponseEntity<Numbers> getNumbersStats(@RequestParam(value = "id", defaultValue = "1") @NonNull long id) {
        if (numbersRepo.existsByTgId(id)) {
            Numbers numbers = numbersRepo.getByTgId(id);
            return new ResponseEntity<>(numbers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
