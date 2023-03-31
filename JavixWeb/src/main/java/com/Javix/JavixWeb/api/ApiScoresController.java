package com.Javix.JavixWeb.api;

import com.Javix.JavixWeb.service.GameService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/score")
public class ApiScoresController {

    @Autowired
    private GameService gameService;

    @GetMapping("/addScore/tic_tac_toe")
    public ResponseEntity<HttpStatus> addScoreTicTacToe(@RequestParam(value = "id", defaultValue = "1") @NonNull long id, @RequestParam(value = "score", defaultValue = "0") @NonNull byte score) {
        if (gameService.addScoreTicTacToe(id, score)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/addScore/2048")
    public ResponseEntity<HttpStatus> addScoreNumbers(@RequestParam(value = "id", defaultValue = "1") @NonNull long id, @RequestParam(value = "score", defaultValue = "0") @NonNull int score) {
        if (gameService.addScoreNumbers(id, score)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
