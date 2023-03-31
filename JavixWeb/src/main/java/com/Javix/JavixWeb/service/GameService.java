package com.Javix.JavixWeb.service;

import org.springframework.stereotype.Service;

@Service
public interface GameService {

    boolean addScoreTicTacToe(long id, byte score);

    boolean addScoreNumbers(long id, int score);
}
