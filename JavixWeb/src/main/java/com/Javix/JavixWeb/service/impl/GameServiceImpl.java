package com.Javix.JavixWeb.service.impl;

import com.Javix.JavixWeb.models.Numbers;
import com.Javix.JavixWeb.models.TicTacToe;
import com.Javix.JavixWeb.repo.NumbersRepo;
import com.Javix.JavixWeb.repo.TicTacToeRepo;
import com.Javix.JavixWeb.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private TicTacToeRepo ticTacToeRepo;

    @Autowired
    private NumbersRepo numbersRepo;

    @Override
    public boolean addScoreTicTacToe(long id, byte score) {
        if (ticTacToeRepo.existsByTgId(id)) {
            TicTacToe ticTacToe = ticTacToeRepo.getByTgId(id);
            int oldScore = ticTacToe.getScore();
            long chatID = ticTacToe.getTgId();
            ticTacToeRepo.setScoreFor(oldScore + score, chatID);
            return true;
        } else {
            return false;
        }
    };

    @Override
    public boolean addScoreNumbers(long id, int score) {
        if (numbersRepo.existsByTgId(id)) {
            Numbers numbers = numbersRepo.getByTgId(id);
            if (numbers.getScore() < score) {
                numbersRepo.setScoreFor(score, id);
                return true;
            }
        }

        return false;
    }
}
