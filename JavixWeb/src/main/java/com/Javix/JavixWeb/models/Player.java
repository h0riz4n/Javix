package com.Javix.JavixWeb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.Javix.JavixWeb.security.AES;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {

    @Id
    @SequenceGenerator(
            name = "hibernate_sequence",
            sequenceName = "hibernate_sequence",
            initialValue = 1,
            allocationSize = 20
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hibernate_sequence"
    )
    @Column (
            name = "id"
    )
    @JsonIgnore
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private byte[] password;

    @Column(name = "tg_id")
    private long tgId;

    public Player(long tgId, String login, String password) {
        this.tgId = tgId;
        this.login = login;
        try {
            this.password = AES.encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
