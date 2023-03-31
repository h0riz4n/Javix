package com.Javix.JavixWeb.javix;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotConfig {

    private String TOKEN;

    public BotConfig() {
        TOKEN = System.getenv("BOT_TOKEN");
    }
}
