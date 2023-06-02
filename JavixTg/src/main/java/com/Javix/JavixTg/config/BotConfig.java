package com.Javix.JavixTg.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter @Setter
public class BotConfig {
    private String botUserName;
    private String botToken;
    private String webHookPath;

    public BotConfig() {
        botUserName = "h0riz4nbot";
        botToken = "6250257674:AAF_C7DJHniH4Yt24LdnkanEvwIKeJ8wI3o";
        webHookPath = "https://javixtelegrambot.ru";
    }
}
