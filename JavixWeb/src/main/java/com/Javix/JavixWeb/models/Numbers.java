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
@Table(name = "numbers_game")
public class Numbers {

    @Id
    @SequenceGenerator(
            name = "numbers_sequence",
            sequenceName = "numbers_sequence",
            initialValue = 1,
            allocationSize = 20
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "numbers_sequence"
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

    public Numbers(long tgId) {
        this.tgId = tgId;
        this.score = 0;
    }
}
