package com.Javix.JavixWeb.repo;

import com.Javix.JavixWeb.models.Player;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PlayerRepo extends JpaRepository<Player, Integer> {

    boolean existsByTgId(@NonNull long tgId);

    Player getByTgId(@NonNull long tgId);

    @Transactional
    void deleteByTgId(@NonNull long id);
}
