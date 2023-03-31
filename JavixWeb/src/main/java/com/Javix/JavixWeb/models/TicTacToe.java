package com.Javix.JavixWeb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tic_tac_toe_game")
public class TicTacToe {

    @Id
    @SequenceGenerator(
            name = "tic_tac_toe_sequence",
            sequenceName = "tic_tac_toe_sequence",
            initialValue = 1,
            allocationSize = 20
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tic_tac_toe_sequence"
    )
    @Column (
            name = "id"
    )
    @JsonIgnore
    private int id;

    @Column(name = "tg_id")
    private long tgId;

    @Column(name = "score")
    private int score;

    public TicTacToe(long tgId) {
        this.tgId = tgId;
        this.score = 0;
    }
}
