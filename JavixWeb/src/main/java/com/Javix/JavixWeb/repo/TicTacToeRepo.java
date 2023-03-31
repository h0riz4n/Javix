package com.Javix.JavixWeb.repo;

import com.Javix.JavixWeb.models.TicTacToe;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TicTacToeRepo extends JpaRepository<TicTacToe, Integer> {

    boolean existsByTgId(@NonNull long tgId);

    TicTacToe getByTgId(@NonNull long tgId);

    @Modifying
    @Transactional
    @Query(value = "update TicTacToe g set g.score = :score where g.tgId = :tg_id")
    int setScoreFor(@NonNull @Param("score") int score, @NonNull @Param("tg_id") long tgId);
}
